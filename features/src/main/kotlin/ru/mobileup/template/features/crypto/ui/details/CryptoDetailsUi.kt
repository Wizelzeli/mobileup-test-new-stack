package ru.mobileup.template.features.crypto.ui.details

import android.os.Build
import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import coil.request.ImageRequest
import me.aartikov.replica.single.Loadable
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.core.theme.OrangeChipsText
import ru.mobileup.template.core.widget.RefreshingProgress
import ru.mobileup.template.core.widget.SwipeRefreshLceWidget
import ru.mobileup.template.features.R
import ru.mobileup.template.features.crypto.domain.CryptoDetails
import ru.mobileup.template.features.crypto.ui.CryptoTheme

@Composable
fun CryptoDetailsUi(
    component: CryptoDetailsComponent,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = CryptoTheme.colors.background.primary
    ) {

        Column(modifier = modifier.fillMaxSize()) {
            component.cryptoDetailsState.data?.name?.let {
                CryptoStatusBarRow(
                    modifier = modifier,
                    name = it,
                    onBackPressed = component::onBackPressed
                )
            }
            SwipeRefreshLceWidget(
                state = component.cryptoDetailsState,
                onRefresh = component::onRefresh,
                onRetryClick = component::onRetryClick

            ) { crypto, refreshing ->
                CryptoDetailsBlock(crypto)
                RefreshingProgress(refreshing, modifier = modifier.padding(top = 4.dp))
            }

        }
    }
}

@Composable
private fun CryptoStatusBarRow(
    name: String,
    onBackPressed: () -> (Unit),
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = CryptoTheme.colors.background.primary,
        elevation = 12.dp
    ) {
        Row {
            Image(
                modifier = modifier
                    .clickable { onBackPressed.invoke() }
                    .padding(20.dp)
                    .size(16.dp),
                painter = painterResource(R.drawable.ic_arrow_back),
                contentDescription = null,
            )

            Text(
                modifier = modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 16.dp),
                text = name,
                style = CryptoTheme.typography.title.boldLarge,
                color = CryptoTheme.colors.text.titleCrypto
            )
        }

    }
}

@Composable
fun CryptoDetailsBlock(
    cryptoDetails: CryptoDetails,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .verticalScroll(enabled = true, state = ScrollState(0))
    ) {
        AsyncImage(
            contentDescription = null,
            model = ImageRequest.Builder(LocalContext.current)
                .data(cryptoDetails.image)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            modifier = modifier
                .padding(top = 10.dp, bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
                .size(90.dp)
                .clip(CircleShape)
                .background(color = CryptoTheme.colors.background.primary)
        )
        Text(
            text = stringResource(R.string.crypto_description),
            style = CryptoTheme.typography.title.boldLarge,
            color = CryptoTheme.colors.text.titleDesc
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            AndroidView(
                factory = { context ->
                    TextView(context).apply {
                        text = Html.fromHtml(
                            cryptoDetails.description,
                            Html.FROM_HTML_MODE_COMPACT
                        )
                    }
                },
                modifier = modifier.padding(top = 16.dp),
            ) {
                it.movementMethod = LinkMovementMethod.getInstance()
                it.setLinkTextColor(OrangeChipsText.toArgb())
                it.textSize = 16f
                it.setTextColor(Color.Black.toArgb())
            }
        } else Text(
            modifier = modifier.padding(top = 16.dp),
            style = CryptoTheme.typography.body.regularLarge,
            color = CryptoTheme.colors.text.titleDesc,
            text = cryptoDetails.description,
        )


        Text(
            modifier = modifier.padding(top = 16.dp),
            text = stringResource(R.string.crypto_categories),
            style = CryptoTheme.typography.title.boldLarge,
            color = CryptoTheme.colors.text.titleDesc
        )
        var categories = ""
        cryptoDetails.categories.forEachIndexed { i, category ->
            categories += if (i == cryptoDetails.categories.size - 1) category
            else "$category, "
        }
        Text(
            modifier = modifier.padding(top = 16.dp, bottom = 32.dp),
            text = categories,
            style = CryptoTheme.typography.body.regularLarge,
            color = CryptoTheme.colors.text.titleDesc
        )


    }
}

@Preview
@Composable
fun CryptoDetailsPreview() {
    AppTheme {
        CryptoDetailsUi(FakeCryptoDetailsComponent())
    }
}

class FakeCryptoDetailsComponent : CryptoDetailsComponent {
    override val cryptoDetailsState = Loadable(
        loading = true,
        data = CryptoDetails(
            name = "Bitcoin",
            categories = listOf(
                "Qwe", "Asd", "Zxc", "Zxc", "Zxc", "Zxc", "Zxc", "Zxc", "Zxc", "Zxc", "Zxc", "Zxc"
            ),
            description = "text text texttexttextte x" +
                    "text text texttexttextte xttexttexttexttexttex ttexttext texttexttext" +
                    "text text texttexttextte xttexttexttexttexttex ttexttext texttexttext" +
                    "text text texttexttextte xttexttexttexttexttex ttexttext texttexttext" +
                    "text text texttexttextte xttexttexttexttexttex ttexttext texttexttext" +
                    "text text texttexttextte xttexttexttexttexttex ttexttext texttexttext" +
                    "text text texttexttextte xttexttexttexttexttex ttexttext texttexttext" +
                    "text text texttexttextte xttexttexttexttexttex ttexttext texttexttext" +
                    "text text texttexttextte xttexttexttexttexttex ttexttext texttexttext" +
                    "text text texttexttextte xttexttexttexttexttex ttexttext texttexttext" +
                    "ttexttexttexttexttex ttexttext texttexttext text text",
            image = ""
        )
    )

    override fun onRetryClick() = Unit

    override fun onBackPressed() = Unit

    override fun onRefresh() = Unit
}