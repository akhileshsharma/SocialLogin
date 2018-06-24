package com.andrew.socialactionssample.presentation.feature.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.andrew.socialactionssample.R
import com.andrew.socialactionssample.data.social.SocialType
import com.andrew.socialactionssample.presentation.viewModels.SocialModel
import com.andrew.socialactionssample.utils.inflate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_social.view.*

/**
 * Created by Andrew on 17.06.2018.
 */

class SocialsAdapter(private var socialsClick: SocialClickListener) :
        RecyclerView.Adapter<SocialsAdapter.SocialViewHolder>()  {

    interface SocialClickListener {
        fun loginClick(socialType: SocialType)
        fun logoutClick(socialType: SocialType)
    }

    private val socials: List<SocialModel> = arrayListOf(SocialModel(SocialType.VK),
            SocialModel(SocialType.TWITTER),
            SocialModel(SocialType.INSTAGRAM),
            SocialModel(SocialType.FACEBOOK))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            SocialViewHolder(parent.inflate(R.layout.item_social))

    override fun getItemCount() = socials.size

    override fun onBindViewHolder(holder: SocialViewHolder, position: Int) {
        holder.bind(socials[position])
    }

    fun updateSocial(socialType: SocialType, token: String, info: String) {
        socials.forEach {
            if (it.socialType == socialType) {
                val pos = socials.indexOf(it)
                it.token = token
                it.info = info
                notifyItemChanged(pos)
            }
        }
    }

    inner class SocialViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(social: SocialModel) {
            Glide.with(itemView)
                    .load(social.socialViewType?.drawableRes)
                    .apply(RequestOptions().circleCrop()
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                    .into(itemView.image_social)
            itemView.text_info.text = social.info
            itemView.text_token.text = social.token
            itemView.button_login.setOnClickListener { socialsClick.loginClick(social.socialType) }
            itemView.button_logout.setOnClickListener { socialsClick.logoutClick(social.socialType) }
        }
    }
}