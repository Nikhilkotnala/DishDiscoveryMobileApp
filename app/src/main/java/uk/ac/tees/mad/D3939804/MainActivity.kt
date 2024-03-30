package uk.ac.tees.mad.D3939804

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import uk.ac.tees.mad.D3939804.auth.SignupScreen
import uk.ac.tees.mad.D3939804.ui.theme.ComposeLoginTheme

import uk.ac.tees.mad.D3939804.ui.screens.NavigationRoutes
import uk.ac.tees.mad.D3939804.ui.screens.authenticatedGraph
import uk.ac.tees.mad.D3939804.ui.screens.unauthenticatedGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLoginTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    DDiscoveryApp()
                }
            }
        }
    }
}

// route destination screens
sealed class DestinationScreen(val route: String){
    object Signup: DestinationScreen("signup")
}

// navigation to screens
@Composable
fun DDiscoveryApp() {
    val vm = hiltViewModel<DdViewModel>()
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = DestinationScreen.Signup.route){
        composable(DestinationScreen.Signup.route){
            SignupScreen(navController = navController, vm = vm)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeLoginTheme {
        DDiscoveryApp()
    }
}