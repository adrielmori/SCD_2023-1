import socket
import threading

connections = []
max_connections = 10
connection_semaphore = threading.Semaphore(max_connections)


class Calculadora:
    @staticmethod
    def somar(val1, val2):
        return val1 + val2

    @staticmethod
    def subtrair(val1, val2):
        return val1 - val2

    @staticmethod
    def mult(val1, val2):
        return val1 * val2

    @staticmethod
    def div(val1, val2):
        return val1 / val2


def handle_client_connection(client_socket):
    try:
        while True:
            data = client_socket.recv(1024)
            if not data:
                break

            values = data.decode("utf-8").strip().split()

            if len(values) == 3:
                ##SOMA
                if values[0] == "1":
                    try:
                        result = Calculadora.somar(int(values[1]), int(values[2]))
                        client_socket.sendall(str(result).encode("utf-8"))
                    except ValueError:
                        client_socket.sendall("invalid value".encode("utf-8"))
                ## SUBTRAÇÃO
                if values[0] == "2":
                    try:
                        result = Calculadora.subtrair(int(values[1]), int(values[2]))
                        client_socket.sendall(str(result).encode("utf-8"))
                    except ValueError:
                        client_socket.sendall("invalid value".encode("utf-8"))
                ## MULTIPICAÇÃO
                if values[0] == "3":
                    try:
                        result = Calculadora.mult(int(values[1]), int(values[2]))
                        client_socket.sendall(str(result).encode("utf-8"))
                    except ValueError:
                        client_socket.sendall("invalid value".encode("utf-8"))
                ## DIVISAO
                if values[0] == "4":
                    try:
                        result = Calculadora.div(int(values[1]), int(values[2]))
                        client_socket.sendall(str(result).encode("utf-8"))
                    except ValueError:
                        client_socket.sendall("invalid value".encode("utf-8"))
            else:
                client_socket.sendall("Erro espaçamento.".encode("utf-8"))

    except Exception as e:
        print(f"Erro na conexão: {e}")
    finally:
        client_socket.close()
        connections.remove(client_socket)


def start_server():
    host = "127.0.0.1"
    port = 1500

    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((host, port))

    server_socket.listen(10)
    print("Servidor pronto e ouvindo em {}:{}".format(host, port))

    try:
        while True:
            client_socket, client_address = server_socket.accept()

            commands = (
                "----MENU----\n1 - soma\n2 - subtrair\n3 - multiplicar\n4 - dividir"
            )
            client_socket.sendall(str(commands).encode("utf-8"))

            print("Nova conexão de {}:{}".format(client_address[0], client_address[1]))

            connections.append(client_socket)

            client_handler = threading.Thread(
                target=handle_client_connection, args=(client_socket,)
            )

            client_handler.start()
    except KeyboardInterrupt:
        print("Servidor encerrado pelo usuário.")
    finally:
        for connection in connections:
            connection.close()

        server_socket.close()


start_server()
