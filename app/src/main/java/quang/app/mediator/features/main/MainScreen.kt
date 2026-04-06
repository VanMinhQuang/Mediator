
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import quang.app.mediator.R
import quang.app.mediator.core.styles.AppColor
import quang.app.mediator.features.main.home.view.HomeView
import quang.app.mediator.features.main.meditate.view.MeditateView
import quang.app.mediator.features.main.music.view.MusicView
import quang.app.mediator.features.main.sleep.view.SleepMainView


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(navController: NavController) {
    val pages = listOf("Home", "Sleep", "Meditate", "Music", "Profile")
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { pages.size }
    )
    val scope = rememberCoroutineScope()
    val currentPage by remember { derivedStateOf { pagerState.currentPage } }
    val isSleepMode = currentPage == 1
    val animatedContainerColor by animateColorAsState(
        targetValue = if (isSleepMode) AppColor.DarkBackground else Color.White,
        animationSpec = tween(durationMillis = 500), label = "navBackground"
    )

    Scaffold(
        contentWindowInsets = WindowInsets.systemBars
            .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom),
        bottomBar = {
            NavigationBar(
                containerColor = animatedContainerColor,
                tonalElevation = 8.dp
            )  {
                pages.forEachIndexed { index, title ->
                    val iconRes = when (index) {
                        0 -> R.drawable.home
                        1 -> R.drawable.moon
                        2 -> R.drawable.meditate
                        3 -> R.drawable.music
                        4 -> R.drawable.user
                        else -> R.drawable.home
                    }
                    NavigationBarItem(
                        selected = currentPage == index,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = AppColor.PrimaryBlue,
                            unselectedIconColor = AppColor.PrimaryBlue,
                            selectedTextColor = AppColor.PrimaryBlue,
                            unselectedTextColor = AppColor.PrimaryBlue,
                            indicatorColor = AppColor.PrimaryBlue
                        ),

                        icon = {
                            Image(
                                painter = painterResource(id = iconRes),
                                contentDescription = title,
                                modifier = Modifier.size(26.dp),
                                colorFilter = ColorFilter.tint(
                                    if (currentPage == index) AppColor.Gray500
                                    else AppColor.Gray700
                                )
                            )
                        },
                        label = {
                            Text(
                                text = title,
                                color = if (currentPage == index) AppColor.Primary else AppColor.Gray700
                            )
                        },
                        onClick = {
                            scope.launch { pagerState.scrollToPage(index) }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        HorizontalPager(

            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) { page ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                when (page) {
                    0 -> HomeView(navController)
                    1 -> SleepMainView()
                    2 -> MeditateView()
                    3 -> MusicView(navController = navController)
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()
    MainScreen(navController)
}
