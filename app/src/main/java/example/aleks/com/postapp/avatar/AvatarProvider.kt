package example.aleks.com.postapp.avatar

/**
 * Created by aleks on 07/01/2018.
 */
interface AvatarProvider {

    fun getAvatarUrl(id: Int): String
}