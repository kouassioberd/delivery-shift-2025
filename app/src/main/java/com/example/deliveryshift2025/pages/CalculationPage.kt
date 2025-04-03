package com.example.deliveryshift2025.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deliveryshift2025.R

@Composable
fun CalculationPage(modifier: Modifier = Modifier) {
    var departureCity by remember { mutableStateOf("Москва") }
    var destinationCity by remember { mutableStateOf("Санкт-Петербург") }
    var packageSize by remember { mutableStateOf("Конверт") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(200.dp))
        // Header Section
        DeliveryHeader()
        Spacer(modifier = Modifier.height(16.dp))

        // Delivery Form Card
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Рассчитать доставку",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))

                // City Selection
                DropdownMenuField(
                    label = "Город отправки",
                    options = listOf("Москва", "Санкт-Петербург", "Новосибирск", "Томск","Новокузнец","Красноярск","Екатеринбург","Хабаровск"),
                    selectedOption = departureCity,
                    onOptionSelected = { departureCity = it }
                )
                Spacer(modifier = Modifier.height(16.dp))

                DropdownMenuField(
                    label = "Город назначения",
                    options = listOf("Санкт-Петербург", "Новосибирск", "Томск", "Москва"),
                    selectedOption = destinationCity,
                    onOptionSelected = { destinationCity = it }
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Package Size Selection
                DropdownMenuField(
                    label = "Размер посылки",
                    options = listOf(
                        "Конверт", "Конверт,42x36x5 см", "Короб XS,17x12x9 см",
                        "Короб S,23x9x10 см", "Короб M,33x25x15 см",
                        "Короб L,32x25x38 см", "Короб XL,60x35x30 см"
                    ),
                    selectedOption = packageSize,
                    onOptionSelected = { packageSize = it }
                )
                Spacer(modifier = Modifier.height(24.dp))

                // Calculate Button
                Button(
                    onClick = {

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text(text = "Рассчитать", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }
}

@Composable
fun DeliveryHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Мы доставим ваш",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "заказ",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Отправляйте посылки в приложении\nШифт Delivery",
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F4F6))
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.ic_box), contentDescription = "Box Icon")
                Spacer(modifier = Modifier.width(8.dp))
                Image(painter = painterResource(id = R.drawable.ic_qr_code), contentDescription = "QR Code")
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Наведите камеру телефона на QR-код",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun DropdownMenuField(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(text = label, fontSize = 16.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(4.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,  // Ensure text & icon align
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = selectedOption)
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_down),  // Ensure this resource exists
                        contentDescription = "Dropdown Arrow"
                    )
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculationPagePreview() {
    CalculationPage()
}

