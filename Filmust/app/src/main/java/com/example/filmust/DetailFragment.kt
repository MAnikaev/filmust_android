package com.example.filmust

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.filmust.databinding.FragmentDetailBinding


class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var binding: FragmentDetailBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailBinding.bind(view)

        //Принимаем айдишник с другого фрагмента
        val id = arguments?.getString("ARG_ID")
        //TODO: Вывод картинки, названия и доп инфы

        binding?.run {
            historyBtn.setOnClickListener(){
                //Сначала идет проверка на наличие в списке и затем смена на нужный цвет
                //TODO: if (id нет в списке)
                //{
                    historyBtn.setBackgroundColor(getResources().getColor(R.color.historyButtonColor_on))
                //TODO: добавление в список
                //}
                //TODO: else(id есть в списке)
                //{
                    historyBtn.setBackgroundColor(getResources().getColor(R.color.buttonColor_off))
                //TODO: удаление из списка
                //}
            }
        }


        binding?.run {
            likeBtn.setOnClickListener(){

                //Сначала идет проверка на наличие в списке и затем смена на нужный цвет
                //TODO: if (id нет в списке)
                //{
                likeBtn.setBackgroundColor(getResources().getColor(R.color.favButtonColor_on))
                //TODO: добавление в список
                //}
                //TODO: else(id есть в списке)
                //{
                likeBtn.setBackgroundColor(getResources().getColor(R.color.buttonColor_off))
                //TODO: удаление из списка
                //}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}