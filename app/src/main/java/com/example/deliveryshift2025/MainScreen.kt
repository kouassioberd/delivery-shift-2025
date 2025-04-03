package com.example.deliveryshift2025

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.deliveryshift2025.pages.CalculationPage
import com.example.deliveryshift2025.pages.HistoryPage
import com.example.deliveryshift2025.pages.ProfilePage


@Composable
fun MainScreen(modifier:Modifier=Modifier){
    val navItemList= listOf(
        NavItem("Расчёт", R.drawable.calculation_icon),
        NavItem("История", R.drawable.history_icon),
        NavItem("Профиль", R.drawable.profile_icon),
    )

    var selectedIndex by remember{
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier= Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                 navItemList.forEachIndexed { index, navItem ->
                     NavigationBarItem(
                         selected =selectedIndex==index,
                         onClick = {
                             selectedIndex=index
                         },
                         icon ={
                             Icon(
                                 painter = painterResource(id = navItem.icon), // Use custom icon
                                 contentDescription = navItem.label // Accessibility description
                             )
                         },
                         label = {
                             Text(text = navItem.label)
                         }
                     )
                 }
            }
        }

    ){ innerPadding->
            ContentScreen(modifier=Modifier.padding(innerPadding),selectedIndex)
    }
}

@Composable
fun ContentScreen(modifier:Modifier=Modifier,selectedIndex:Int){
    when(selectedIndex)   {
        0-> CalculationPage()
        1-> HistoryPage()
        2-> ProfilePage()

    }

}