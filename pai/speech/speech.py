import pyttsx3
from py4j.java_gateway import JavaGateway, CallbackServerParameters, GatewayParameters, launch_gateway


def start_server():
    print("Here")
    java = JavaGateway(gateway_parameters=GatewayParameters(port=1001))
    engine = pyttsx3.init()

    rate = engine.getProperty('rate')
    engine.setProperty('rate', 150)

    volume = engine.getProperty('volume')
    engine.setProperty('volume', 1.0)

    voices = engine.getProperty('voices')
    engine.setProperty('voice', voices[0].id)

    engine.say(java.jvm.kai.kaibrain.pai.PaiServer.SpeechServer().getWords())
    engine.runAndWait()
    engine.stop()


start_server()
