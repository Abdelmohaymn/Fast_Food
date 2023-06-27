package com.example.fastfood.util

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.fastfood.R
import com.example.fastfood.State
import com.example.fastfood.adapters.BaseAdapter
import com.example.fastfood.model.recipesList.ExtendedIngredient
import kotlin.math.roundToInt


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .into(view)
    }
}

@BindingAdapter(value=["imageUrl","lotti"])
fun loadImagee(view: ImageView, imageUrl: String?,lotti: LottieAnimationView) {

    lotti.visibility = View.VISIBLE
    lotti.setAnimation("loading.json")
    view.isEnabled=false

    Glide.with(view.context)
        .load(imageUrl)
        .listener(object : RequestListener<Drawable>{
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                lotti.setAnimation("failed.json")
                lotti.loop(false)
                lotti.playAnimation()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                lotti.cancelAnimation()
                lotti.visibility = View.GONE
                view.isEnabled=true
                return false
            }
        })
        .into(view)

}

@SuppressLint("NewApi")
@BindingAdapter("textHtml")
fun loadText(view:TextView,t:String?){
    if(t!=null){
        var index = t.indexOf("<a href=")
        var s=t;
        if(index!=-1)s = s.substring(0,index)
        index = s.lastIndexOf('.')
        if(index!=-1)s = s.substring(0,index+1)
        val txt = Html.fromHtml(s, Html.FROM_HTML_MODE_LEGACY)
        view.text = txt
    }

}

@BindingAdapter(value = ["title","textForList"])
fun loadTextForList(view:TextView,title:String?,list:List<String?>?){

    var s = ""
    if(list != null && list.isNotEmpty()){
        s=title?:""
        view.visibility= View.VISIBLE
    }else{view.visibility= View.GONE}
    list?.forEachIndexed { index, st->
        s+=st
        if(index!=list.size-1)s+=" - "
    }
    view.text = s
}

@BindingAdapter(value = ["adapterList"])
fun<T> setAdapterList(view: RecyclerView, items:List<T>?){
    if(items!=null){
        (view.adapter as BaseAdapter<T>?)?.setList(items)
    }else{
        (view.adapter as BaseAdapter<T>?)?.setList(emptyList())
    }
}
//////
/*@SuppressLint("NewApi")
@BindingAdapter("cardViewBackground")
fun changeBackground(view: CardView, x:Boolean){
    view.setCardBackgroundColor(view.resources.getColor(specificColor(),null));
}*/

@BindingAdapter(value = ["textOptions","status"])
fun text2Options(view:TextView, item: ExtendedIngredient, status:Boolean){

    var s = (item.amount?.times(10)?.roundToInt())?.div(10.0).toString()+" "
    s += if(status){
        item.measures?.us?.unitShort
    }else{
        item.measures?.metric?.unitShort
    }
    view.text = s
}

@BindingAdapter(value=["value","showWhenLoading"])
fun <T>showWhenLoading(view:View,value:Boolean,state:State<T>?){
    checkForVisibility(view,(!value || state is State.Loading))
}

@BindingAdapter(value=["value","showWhenSuccess"])
fun <T>showWhenSuccess(view:View,value:Boolean,state:State<T>?){
    checkForVisibility(view,(!value || state is State.Success))
}

@BindingAdapter(value=["value","showWhenError"])
fun <T>showWhenError(view:View,value:Boolean,state:State<T>?){
    if(value && state is State.Error){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["imageFav"])
fun changeImageFav(view:ImageView,status: Boolean){
    if(status){
        view.setImageResource(R.drawable.ic_favorite)
    }else{
        view.setImageResource(R.drawable.ic_favorite_border)
    }
}

@BindingAdapter(value = ["showIfEmpty"])
fun <T> showIfEmpty(view:View,items:List<T>?){
    checkForVisibility(view,items.isNullOrEmpty())
}

@BindingAdapter("android:src")
fun putImageResourc(imageView: ImageView,res:Int){
    imageView.setImageResource(res)
}

fun checkForVisibility(view:View,status:Boolean){
    if(status){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}
