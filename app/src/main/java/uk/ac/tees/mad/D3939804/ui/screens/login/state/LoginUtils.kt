package uk.ac.tees.mad.D3939804.ui.screens.login.state

import uk.ac.tees.mad.D3939804.R
import uk.ac.tees.mad.D3939804.ui.common.state.ErrorState

val emailOrMobileEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_email_mobile
)

val passwordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_password
)