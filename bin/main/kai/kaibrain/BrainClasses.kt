package kai.kaibrain

internal class BrainClasses() {

    internal enum class BrainFileType {
        ChatFile, ArticleOpinionFile, UnknownCorrupt
    }

    public enum class BrainReturnMessageInfo {
        MessageInputType, MessageResultType, MessageInput, MessageResult, All
    }
}