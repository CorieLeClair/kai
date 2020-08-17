package kai.task.spotify

import com.wrapper.spotify.SpotifyApi
import com.wrapper.spotify.exceptions.SpotifyWebApiException
import org.apache.hc.core5.http.ParseException
import java.io.IOException
import java.util.concurrent.CancellationException
import java.util.concurrent.CompletionException


object AddItemToUsersPlaybackQueueExample {
    private const val accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk"
    private const val trackUri = "spotify:track:01iyCAUm8EvOFqVWYJ3dVX"
    private val spotifyApi = SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build()
    private val addItemToUsersPlaybackQueueRequest = spotifyApi
            .addItemToUsersPlaybackQueue(trackUri) //    .device_id("5fbb3ba6aa454b5534c4ba43a8c7e8e45a63ad0e")
            .build()
}