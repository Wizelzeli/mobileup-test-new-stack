package ru.mobileup.template.features.crypto.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.features.R
import ru.mobileup.template.features.crypto.domain.CryptoValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import me.aartikov.replica.single.Loadable
import ru.mobileup.template.core.widget.EmptyPlaceholder
import ru.mobileup.template.core.widget.RefreshingProgress
import ru.mobileup.template.core.widget.SwipeRefreshLceWidget
import ru.mobileup.template.features.crypto.domain.Crypto
import ru.mobileup.template.features.crypto.domain.CryptoId
import ru.mobileup.template.features.crypto.domain.CryptoValueName
import ru.mobileup.template.features.crypto.ui.CryptoTheme
import java.util.*

@Composable
fun CryptoListUi(
    component: CryptoListComponent,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            CryptoValuesRow(
                values = component.values,
                selectedValueName = component.selectedValue,
                onValueClick = component::onValueClick
            )

            SwipeRefreshLceWidget(
                state = component.cryptosState,
                onRefresh = component::onRefresh,
                onRetryClick = component::onRetryClick
            ) { cryptos, refreshing ->
                if (cryptos.isNotEmpty()) {
                    CryptosListContent(
                        component = component,
                        cryptos = cryptos,
                        onCryptoClick = component::onCryptoClick
                    )
                } else {
                    EmptyPlaceholder(description = stringResource(R.string.pokemons_empty_description))
                }
                RefreshingProgress(refreshing)
            }
        }
    }
}

@Composable
private fun CryptosListContent(
    component: CryptoListComponent,
    cryptos: List<Crypto>,
    onCryptoClick: (CryptoId) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        items(
            items = cryptos,
            key = { it.id }
        ) { crypto ->
            CryptoItem(
                component = component,
                crypto = crypto,
                onClick = { onCryptoClick(crypto.id) }
            )

            if (crypto !== cryptos.lastOrNull()) {
                Divider()
            }
        }
    }
}

@Composable
private fun CryptoItem(
    component: CryptoListComponent,
    crypto: Crypto,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(Modifier.clickable(onClick = onClick)) {
        Column {
            AsyncImage(
                contentDescription = null,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(crypto.image)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .padding(top = 8.dp, start = 16.dp, bottom = 8.dp, end = 8.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color = CryptoTheme.colors.background.primary)
            )
        }
        Column(modifier.weight(1.0F)) {
            Text(
                modifier = modifier
                    .padding(top = 8.dp),
                text = crypto.name,
                color = CryptoTheme.colors.text.primaryCrypto,
                style = CryptoTheme.typography.body.regularLarge
            )
            Text(
                modifier = modifier,
                text = crypto.symbol.uppercase(),
                color = CryptoTheme.colors.text.shortCrypto,
                style = CryptoTheme.typography.body.regularNormal
            )
        }
        Column(modifier.padding(end = 16.dp)) {
            when (component.selectedValue) {
                CryptoValue.Eur.name ->
                    Text(
                        modifier = modifier
                            .padding(top = 8.dp)
                            .align(Alignment.End),
                        text = stringResource(
                            R.string.crypto_eur,
                            "%,f".format(Locale.ENGLISH, crypto.currentPrice).dropLast(4)
                        ),
                        color = CryptoTheme.colors.text.primaryCrypto,
                        style = CryptoTheme.typography.body.boldSmall
                    )
                CryptoValue.Usd.name ->
                    Text(
                        modifier = modifier
                            .padding(top = 8.dp)
                            .align(Alignment.End),
                        text = stringResource(
                            R.string.crypto_usd,
                            "%,f".format(Locale.ENGLISH, crypto.currentPrice).dropLast(4)
                        ),
                        color = CryptoTheme.colors.text.primaryCrypto,
                        style = CryptoTheme.typography.body.boldSmall
                    )
            }
            if (crypto.priceChangePercentage >= 0) {
                Text(
                    modifier = modifier
                        .align(Alignment.End),
                    text = stringResource(
                        R.string.crypto_percent_plus,
                        "%.2f".format(Locale.ROOT, crypto.priceChangePercentage)
                    ),
                    color = CryptoTheme.colors.text.plusCryptoPercent,
                    style = CryptoTheme.typography.body.regularNormal
                )
            } else
                Text(
                    modifier = modifier
                        .align(Alignment.End),
                    text = stringResource(
                        R.string.crypto_percent_minus,
                        "%.2f".format(Locale.ROOT, crypto.priceChangePercentage)
                    ),
                    color = CryptoTheme.colors.text.minusCryptoPercent,
                    style = CryptoTheme.typography.body.regularNormal
                )
        }
    }
}

@Preview
@Composable
fun CryptoListUiPreview() {
    AppTheme {
        CryptoListUi(FakeCryptoListComponent())
    }
}


/*@Composable
fun CryptoItemPreview() {
    AppTheme {
        CryptoItem(
            FakeCryptoListComponent(),
            Crypto(
                id = CryptoId("1"),
                image = "",
                name = "Bitcoin",
                currentPrice = 120.34,
                priceChangePercentage = 3.33,
                symbol = "btc"
            ),
            onClick = { Unit }
        )
    }
}*/

@Composable
private fun CryptoValuesRow(
    values: List<CryptoValue>,
    selectedValueName: CryptoValueName,
    onValueClick: (CryptoValueName) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = CryptoTheme.colors.background.primary,
        elevation = 12.dp
    ) {
        Column {
            Text(
                modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp),
                text = stringResource(R.string.crypto_list),
                style = CryptoTheme.typography.title.boldLarge
            )
            Row(
                modifier = modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                values.forEach {
                    CryptoValueItem(
                        type = it,
                        isSelected = it.name.value == selectedValueName.value,
                        onClick = { onValueClick(it.name) }
                    )
                }
            }
        }
    }
}


class FakeCryptoListComponent : CryptoListComponent {

    override val values = listOf(
        CryptoValue.Eur,
        CryptoValue.Usd
    )

    override var selectedValue = values[0].name

    override val cryptosState = Loadable(
        loading = true,
        data = listOf(
            Crypto(
                id = CryptoId("1"),
                image = "",
                name = "Bitcoin",
                currentPrice = 120.3432,
                priceChangePercentage = 3.33005,
                symbol = "btc"
            ),
            Crypto(
                id = CryptoId("5"),
                image = "",
                name = "Etherium",
                currentPrice = 120000.342,
                priceChangePercentage = 3.333,
                symbol = "ETH"
            ),
            Crypto(
                id = CryptoId("7"),
                image = "",
                name = "Doge",
                currentPrice = 133.0,
                priceChangePercentage = -23.000,
                symbol = "DOG"
            )
        )
    )

    override fun onValueClick(cryptoValueName: CryptoValueName) = Unit

    override fun onCryptoClick(cryptoId: CryptoId) = Unit

    override fun onRetryClick() = Unit

    override fun onRefresh() = Unit
}


