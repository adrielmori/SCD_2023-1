from threading import Thread

class Node:

    def __init__(self, data):
        self.data = data
        self.left = None
        self.right = None

class BinaryTree:

    def __init__(self, root):
        self.root = root

    def traverse(self, node, results):
        if node is None:
            return

        results.append(node.data)

        self.traverse(node.left, results)
        self.traverse(node.right, results)

    def bi_thread_search(self, node, results):
        if node is None:
            return

        thread1 = Thread(target=self.traverse, args=(node.left, results))
        thread2 = Thread(target=self.traverse, args=(node.right, results))

        thread1.start()
        thread2.start()

        thread1.join()
        thread2.join()

    def print_results(self, results):
        for result in results:
            print(result)

if __name__ == "__main__":
    # Criação da árvore binária com o nó raiz contendo "judy".
    tree = BinaryTree(Node("judy"))

    # Construção da árvore binária:
    tree.root.left = Node("bill")
    tree.root.right = Node("mary")

    tree.root.left.left = Node("alcie")
    tree.root.left.right = Node("fred")

    tree.root.right.right = Node("tom")

    tree.root.left.right.left = Node("dave")
    tree.root.left.right.right = Node("jane")

    tree.root.left.right.left.right = Node("joe")

    # Lista para armazenar os resultados da busca.
    results = []

    # Inicia a busca bi-thread na árvore e preenche a lista de resultados.
    tree.bi_thread_search(tree.root, results)

    # Imprime os resultados da busca.
    tree.print_results(results)