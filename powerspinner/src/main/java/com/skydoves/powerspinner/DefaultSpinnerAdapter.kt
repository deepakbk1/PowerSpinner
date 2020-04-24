/*
 * Designed and developed by 2019 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.powerspinner

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_default.view.*

/** DefaultSpinnerAdapter is a default adapter composed of string items. */
class DefaultSpinnerAdapter(
        powerSpinnerView: PowerSpinnerView
) : RecyclerView.Adapter<DefaultSpinnerAdapter.DefaultSpinnerViewHolder>(),
        PowerSpinnerInterface<String> {

    override val spinnerView: PowerSpinnerView = powerSpinnerView
    override var onSpinnerItemSelectedListener: OnSpinnerItemSelectedListener<String>? = null

    private val spinnerItems: MutableList<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultSpinnerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DefaultSpinnerViewHolder(layoutInflater.inflate(R.layout.item_default, parent, false))
    }

    override fun onBindViewHolder(holder: DefaultSpinnerViewHolder, position: Int) {
        val item = this.spinnerItems[position]
        holder.itemView.apply {
            item_default_text.apply {
                text = item
                typeface = spinnerView.typeface
                gravity = spinnerView.gravity
                setTextSize(TypedValue.COMPLEX_UNIT_PX, spinnerView.textSize)
                setTextColor(spinnerView.currentTextColor)
                setPadding(spinnerView.paddingLeft, spinnerView.paddingTop, spinnerView.paddingRight,
                        spinnerView.paddingBottom)
            }

            setOnClickListener { notifyItemSelected(position) }
        }
    }

    override fun setItems(itemList: List<String>) {
        this.spinnerItems.clear()
        this.spinnerItems.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun notifyItemSelected(index: Int) {
        this.spinnerView.notifyItemSelected(index, this.spinnerItems[index])
        this.onSpinnerItemSelectedListener?.onItemSelected(index, this.spinnerItems[index])
    }

    override fun getItemCount() = this.spinnerItems.size

    class DefaultSpinnerViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
