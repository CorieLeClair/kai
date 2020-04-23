from py4j.java_gateway import JavaGateway, GatewayParameters
from pai.create_chat_files import PaiChatFiles


def train_chat_messages():
    java = JavaGateway(gateway_parameters=GatewayParameters(port=1000))
    kai = java.jvm.kai.paibrain.PaiServer.TrainChatServer()

    dictionary_java = kai.getDict()

    print(dictionary_java)
    print(list(dictionary_java))

    for item in list(dictionary_java):
        PaiChatFiles.create_json(item, kai.getDict()[item], "Ask001")

    PaiChatFiles.create_chat_file(kai.getFileResult())
    print("a")
    print(kai.getFileResult())
    kai.finished(kai.getFileResult())


train_chat_messages()
