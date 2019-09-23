package com.raqun.movies.core.navigation.features

import android.content.Intent
import com.raqun.movies.core.navigation.PACKAGE_NAME
import com.raqun.movies.core.navigation.loadIntentOrReturnNull

object TvShowDetails : Feature<Intent> {

    private const val SHOW_DETAIL = "$PACKAGE_NAME.shows.details.presentation.TvShowDetailsActivity"

    const val BUNDLE_ID = "id"

    override val dynamicStart: Intent?
        get() = SHOW_DETAIL.loadIntentOrReturnNull()

    fun dynamicStart(id: Int) = dynamicStart?.putExtra(BUNDLE_ID, id)
}
