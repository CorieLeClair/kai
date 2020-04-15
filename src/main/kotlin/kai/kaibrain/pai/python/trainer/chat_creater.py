from py4j.java_gateway import JavaGateway, CallbackServerParameters, GatewayParameters, launch_gateway
import article.article
import nltk
import json

data = {'config': {
    "brain_file_type": "chat_file"
}, 'messages': {}}


def train_chat_messages(kai):
    dictionary_java = kai.getDict()

    print(dictionary_java)
    print(list(dictionary_java))

    for item in list(dictionary_java):
        create_json(item, kai.getDict()[item])

    create_chat_file(kai.getFileResult())


# helper
def input_type(sent):
    # posts for chat
    posts = nltk.corpus.nps_chat.xml_posts()[:10000]

    # dialogue systems
    def dialogue_act_features(post):
        features = {}
        for word in nltk.word_tokenize(post):
            features['contains({})'.format(word.lower())] = True
        return features

    # training systems
    feature_sets = [(dialogue_act_features(post.text), post.get('class')) for post in posts]
    size = int(len(feature_sets) * 0.1)
    train_set, test_set = feature_sets[size:], feature_sets[:size]
    classifier = nltk.NaiveBayesClassifier.train(train_set)

    # returns the input type
    return classifier.classify(dialogue_act_features(sent))


def create_json(message, response):
    print(message + response)
    data["messages"][message] = []

    data['messages'][message].append({
        'message_contain_type': input_type(message),
        'message_contain_string': message,
        'message_result_string': response,
        "message_result_type": input_type(response)
    })


def create_chat_file(file_result):
    json_file = open(file_result, "w")
    json.dump(data, json_file, indent=2)


def start_server():
    java = JavaGateway(gateway_parameters=GatewayParameters(port=1000))
    trainer_type = java.jvm.kai.kaibrain.pai.PaiServer.TrainChatServer().getTrainType()

    if trainer_type == 1:
        kai = java.jvm.kai.kaibrain.pai.PaiServer.TrainChatServer()
        train_chat_messages(kai)


start_server()
