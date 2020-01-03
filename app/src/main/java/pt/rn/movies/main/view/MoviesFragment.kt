package pt.rn.movies.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import pt.rn.movies.arch.lifecyclecomponents.LoggingLifecycleAwareComponent
import pt.rn.movies.databinding.FragmentMoviesBinding
import pt.rn.movies.main.viewmodel.MoviesViewModel

class MoviesFragment : Fragment() {

    private val moviesViewModel: MoviesViewModel by sharedViewModel()

    private lateinit var dataBinding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LoggingLifecycleAwareComponent.bindObserver(this)

        dataBinding = FragmentMoviesBinding.inflate(inflater, container, false)

        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeLiveData()
    }

    private fun observeLiveData(){

        // TODO

    }

}