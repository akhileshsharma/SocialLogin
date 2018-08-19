package com.andrew.social.login.instagram.explicit

import android.app.Activity
import android.text.TextUtils
import com.andrew.social.login.core.SocialType
import com.andrew.social.login.core.action.BaseWebSocialLoginAction

/**
 * Created by Andrew on 24.06.2018
 */

class InstagramLoginActionImpl(activity: Activity,
                               clientId: String,
                               redirectUrl: String,
                               scope: String = "") : BaseWebSocialLoginAction(activity, SocialType.INSTAGRAM, INSTAGRAM_REQUEST_CODE) {

    companion object {
        private const val INSTAGRAM_REQUEST_CODE = 10001
    }

    override var url = "https://instagram.com/oauth/authorize/" +
            "?client_id=$clientId" +
            "&redirect_uri=$redirectUrl" +
            "&response_type=code"

    init {
        if (!TextUtils.isEmpty(scope)) {
            url += "&scope=$scope"
        }
    }
}