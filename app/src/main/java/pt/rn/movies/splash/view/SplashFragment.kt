package pt.rn.movies.splash.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import pt.rn.movies.arch.extensions.navigateToAndFinish
import pt.rn.movies.arch.lifecyclecomponents.LoggingLifecycleAwareComponent
import pt.rn.movies.databinding.FragmentSplashBinding
import pt.rn.movies.splash.viewmodel.SplashViewModel
import pt.rn.movies.splash.viewmodel.entities.SplashViewState

class SplashFragment : Fragment() {

    private val splashViewModel: SplashViewModel by sharedViewModel()

    private lateinit var dataBinding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LoggingLifecycleAwareComponent.bindObserver(this)

        dataBinding = FragmentSplashBinding.inflate(inflater, container, false).apply {
            viewModel = splashViewModel
        }

        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeLiveData()

        savedInstanceState?.let {
            dataBinding.imageRotation = it.getFloat(IMAGE_ROTATION_EXTRA_KEY)
        }
    }

    private fun observeLiveData() {
        splashViewModel.stateLiveData.observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is SplashViewState.ShowSplash -> startAnimatingSplash()
                is SplashViewState.FinishSplash -> navigateToMainActivity()
            }
        })
    }

    private fun navigateToMainActivity() {
        dataBinding.rotateImage = false

        SplashFragmentDirections.actionSplashFragmentToMainActivity()
            .navigateToAndFinish(activity, dataBinding.fragmentSplashRoot)
    }

    private fun startAnimatingSplash() {
        dataBinding.rotateImage = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putFloat(IMAGE_ROTATION_EXTRA_KEY, dataBinding.fragmentSplashImage.rotation)
    }

    companion object {
        private const val IMAGE_ROTATION_EXTRA_KEY = "ImageRotationExtraKey"
    }

}