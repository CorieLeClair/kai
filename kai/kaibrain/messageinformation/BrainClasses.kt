package kai.kaibrain.messageinformation

class BrainClasses() {

    enum class Platform{
        Windows, Mac, Linux, Android
    }

    internal enum class BrainFileType {
        ChatFile, ArticleOpinionFile, UnknownCorrupt
    }

     enum class BrainReturnMessageInfo {
        MessageInputType, MessageResultType, MessageInput, MessageResult, Desc
    }

    class MessageType{
        enum class Question{

            // 001 : How Do You Feel About, 002 : What is this, 003: Who is this, 004 : Where is this, 005 : How is this done

            Ask001, Ask001Specific, Ask002, Ask002Specific, Ask003, Ask003Specific, Ask004, Ask004Specific, Ask005, Ask005Specific, Unknown
        }

        enum class Statement{

            // 001 : Stating emotion, 002 : Stating fact

            Statement001, Statement002, Unknown
        }
    }
}