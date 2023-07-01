import socket
import threading


def receive_messages(client_socket):
    try:
        while True:
            data = client_socket.recv(1024)
            if not data:
                break
            if "\n" in data.decode("utf-8"):
                print(data.decode("utf-8"))
            else:
                print("Resposta: ", data.decode("utf-8"))

    except Exception as e:
        print(f"Erro na conexão: {e}")
    finally:
        client_socket.close()


def start_client():
    host = "127.0.0.1"
    port = 1500

    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    try:
        client_socket.connect((host, port))

        message_receiver = threading.Thread(
            target=receive_messages, args=(client_socket,)
        )
        message_receiver.start()

        while True:
            values = input().strip()
            client_socket.sendall(values.encode("utf-8"))
    except KeyboardInterrupt:
        print("Cliente encerrado pelo usuário.")
    except Exception as e:
        print(f"Erro na conexão: {e}")
    finally:
        client_socket.close()


start_client()
