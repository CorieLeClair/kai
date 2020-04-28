package kai.kaibrain

class BrainClasses() {

    internal enum class BrainFileType {
        ChatFile, ArticleOpinionFile, UnknownCorrupt
    }

     enum class BrainReturnMessageInfo {
        MessageInputType, MessageResultType, MessageInput, MessageResult, Desc, All
    }

    class MessageType{
        enum class Question{

            // 001 : How Do You Feel About, 002 : What is this, 003: Who is this, 004 : Where is this, 005 : How is this done

            Ask001, Ask002, Ask003, Ask004, Ask005, Unknown
        }

        enum class Statement{

            // 001 : Stating emotion, 002 : Stating fact

            Statement001, Statement002, Unknown
        }
    }
}