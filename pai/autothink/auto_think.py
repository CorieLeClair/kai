from py4j.java_gateway import JavaGateway, CallbackServerParameters, GatewayParameters, launch_gateway
from pai.pai_think_helper import PaiSpeechTagging
from pai.pai_think_helper import PaiSentenceHelper
from pai.create_chat_files import PaiChatFiles


class AutoThinkSystems:

    @staticmethod
    def get_attributes(noun, all_sent):
        attributes = []

        for item in all_sent:
            if noun in item:
                att = PaiSpeechTagging().find_adj(item)
                for attribute in att:
                    if len(list(attribute)) < 1:
                        del att[att.index(attribute)]

                if len(att) > 0:
                    for x in att:
                        attributes.append(x)

        return attributes

    @staticmethod
    def complete_thought(noun, attributes):
        print(attributes)
        # finish thought helpers

        thoughts = []
        finished_thought = []

        for item in attributes:
            thoughts.append(str(item).replace("]", "").replace("[", "").replace("'", ""))

        # generate sentence

        return PaiSentenceHelper().sentence_generator_desc(noun, thoughts)
        # sentence verifying

    @staticmethod
    def start_server():
        # connection to kai
        java = JavaGateway(gateway_parameters=GatewayParameters(port=1002))
        # all nouns in messages
        noun_list = PaiSpeechTagging().find_nouns_all(
            java.jvm.kai.paibrain.PaiServer.AutoThinkServer().getAllMessages())
        # attributes of noun
        att = AutoThinkSystems().get_attributes(
            "Trump", java.jvm.kai.paibrain.PaiServer.AutoThinkServer().getAllMessages())
        # complete thoughts
        print(AutoThinkSystems().complete_thought("Trump", att))
        PaiChatFiles().create_json("Trump", AutoThinkSystems().complete_thought("Trump", att),
                                   "Desc")
        PaiChatFiles.create_chat_file("C:/Users/corie/OneDrive/Documents/kai/KaiCompanion/documents/autogen/1.json")


AutoThinkSystems().start_server()
