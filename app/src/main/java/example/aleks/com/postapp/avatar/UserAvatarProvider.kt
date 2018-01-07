package example.aleks.com.postapp.avatar

import example.aleks.com.postapp.BuildConfig

/**
 * Created by aleks on 07/01/2018.
 */
class UserAvatarProvider : AvatarProvider {

    override fun getAvatarUrl(id: Int) = BuildConfig.AVATAR.replace("{unique_id}", id.toString())
}