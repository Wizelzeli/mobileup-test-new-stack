package ru.mobileup.template.features.crypto.ui.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.features.crypto.domain.CryptoValue
import ru.mobileup.template.features.crypto.ui.CryptoTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CryptoValueItem(
    type: CryptoValue,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Surface(
        modifier = modifier,
        onClick = { onClick?.invoke() },
        enabled = onClick != null,
        shape = RoundedCornerShape(48.dp),
        color = when (isSelected) {
            true -> CryptoTheme.colors.background.selectedChips
            else -> CryptoTheme.colors.background.disabledChips
        }
    ) {
        Text(
            color = when (isSelected) {
                true -> CryptoTheme.colors.text.selectedChips
                else -> CryptoTheme.colors.text.disabledChips
            },
            text = type.name.value,
            style = CryptoTheme.typography.body.regularLarge,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
        )
    }
}

@Preview
@Composable
fun PokemonTypeItemPreview() {
    var isSelected by remember { mutableStateOf(false) }
    AppTheme {
        CryptoValueItem(
            type = CryptoValue.Eur,
            isSelected = isSelected,
            onClick = {
                isSelected = !isSelected
            }
        )
    }
}