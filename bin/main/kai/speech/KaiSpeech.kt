package kai.speech

import java.util.Locale
import javax.speech.Central
import javax.speech.synthesis.Synthesizer
import javax.speech.synthesis.SynthesizerModeDesc
import java.io.File
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public fun speak(text : String){
    try
    {
      // Set property as Kevin Dictionary
      System.setProperty(
        "freetts.voices",
        ("com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory"))

        println(File("com/sun/speech/freetts/en/us/cmu_us_kal/").list())

      println(System.getProperty("freetts.voices"))
      // Register Engine
      Central.registerEngineCentral(
        ("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral"))
      // Create a Synthesizer
      val synthesizer = Central.createSynthesizer(
        SynthesizerModeDesc(Locale.US))
      // Allocate synthesizer
      synthesizer.allocate()
      // Resume Synthesizer
      synthesizer.resume()
      // Speaks the given text
      // until the queue is empty.
      synthesizer.speakPlainText(
        text, null)
      synthesizer.waitEngineState(
        Synthesizer.QUEUE_EMPTY)
      // Deallocate the Synthesizer.
      //synthesizer.deallocate()
    }
    catch (e:Exception) {
      e.printStackTrace()
    }
}

public fun mborlaTest(){
    System.setProperty("mbrola.base", "/usr/bin/mbrola")
    val voiceManager = VoiceManager.getInstance()
    val voice = voiceManager.getVoice("mbrola_us1")

    voice.allocate()
    voice.speak("hello")
}
