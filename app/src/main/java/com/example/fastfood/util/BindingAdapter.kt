package com.example.fastfood.util

import android.annotation.SuppressLint
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

@SuppressLint("NewApi")
@BindingAdapter("cardViewBackground")
fun changeBackground(view: CardView, x:Boolean){
    view.setCardBackgroundColor(view.resources.getColor(specificColor(),null));
}

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

@BindingAdapter(value=["showWhenLoading"])
fun <T>showWhenLoading(view:View,state:State<T>?){
    if(state is State.Loading){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter(value=["showWhenSuccess"])
fun <T>showWhenSuccess(view:View,state:State<T>?){
    if(state is State.Success){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter(value=["showWhenError"])
fun <T>showWhenError(view:View,state:State<T>?){
    if(state is State.Error){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}