package uk.ac.tees.mad.D3939804.ui.common.state

import androidx.annotation.StringRes
import uk.ac.tees.mad.D3939804.R

/**
 * Error state holding values for error ui
 */
data class ErrorState(
    val hasError: Boolean = false,
    @StringRes val errorMessageStringResource: Int = R.string.empty_string
)