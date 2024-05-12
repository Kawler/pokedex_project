package com.artemla.pokedex.ui.activities

import android.graphics.Color
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.artemla.pokedex.R
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment

class PokedexAppIntro : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(
            AppIntroFragment.newInstance(
                "Welcome to Pokedex",
                "Explore the world of Pokémon with our comprehensive Pokedex app." +
                        " View detailed information about all your favorite Pokémon" +
                        ", including their types, weaknesses, and evolutions.",
                R.drawable.splash_1,
                Color.WHITE,
                titleColor = ContextCompat.getColor(this, R.color.black),
                descriptionColor = ContextCompat.getColor(this, R.color.black),
            )
        )
        addSlide(
            AppIntroFragment.newInstance(
                "Discover Pokémon Details",
                "Dive deep into the details of each Pokémon. Learn about their types to " +
                        "understand their strengths and weaknesses. " +
                        "Explore their evolution chains to see how they evolve and grow",
                R.drawable.splash_2,
                Color.WHITE,
                titleColor = ContextCompat.getColor(this, R.color.black),
                descriptionColor = ContextCompat.getColor(this, R.color.black),

                )
        )
        addSlide(
            AppIntroFragment.newInstance(
                "Explore Pokémon List",
                "Browse through our extensive list of Pokémon to find the ones you love. " +
                        "Filter by type, search by name, and discover new favorites. " +
                        "Get ready to embark on your journey to become a Pokémon Master!",
                R.drawable.splash_3,
                Color.WHITE,
                titleColor = ContextCompat.getColor(this, R.color.black),
                descriptionColor = ContextCompat.getColor(this, R.color.black),
            )
        )
        isIndicatorEnabled = true

// Change Indicator Color
        setIndicatorColor(
            selectedIndicatorColor = ContextCompat.getColor(this, R.color.blue),
            unselectedIndicatorColor = getColor(R.color.dark_grey)
        )

        setColorDoneText(ContextCompat.getColor(this, R.color.black))
        setColorSkipButton(ContextCompat.getColor(this, R.color.black))
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Decide what to do when the user clicks on "Done"
        finish()
    }
}