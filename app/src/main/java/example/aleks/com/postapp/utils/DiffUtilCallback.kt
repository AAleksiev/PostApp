package example.aleks.com.postapp.utils

import android.support.v7.util.DiffUtil
import example.aleks.com.postapp.models.UniqueId

/**
 * Created by aleks on 06/01/2018.
 */
class DiffUtilCallback<T : UniqueId>(private val current: List<T>, private val next: List<T>) : DiffUtil.Callback() {

    //region DiffUtil.Callback implementation
    override fun getOldListSize(): Int = current.size

    override fun getNewListSize(): Int = next.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        val currentItem = current[oldItemPosition]

        val nextItem = next[newItemPosition]

        return currentItem.uniqueId.equals(nextItem.uniqueId, true)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        val currentItem = current[oldItemPosition]

        val nextItem = next[newItemPosition]

        return currentItem == nextItem
    }
    //endregion
}