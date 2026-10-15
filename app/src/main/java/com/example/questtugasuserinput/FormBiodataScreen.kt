package com.example.questtugasuserinput

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormBiodataApp() {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var textNamaDepan by remember { mutableStateOf("") }
    var textNamaBelakang by remember { mutableStateOf("") }
    var textEmail by remember { mutableStateOf("") }
    var textTglLahir by remember { mutableStateOf("") }
    var textExperience by remember { mutableStateOf("") }
    var isClicked by remember { mutableStateOf(false) }

    var checkAndroid by remember { mutableStateOf(false) }
    var checkUiUx by remember { mutableStateOf(false) }
    var checkWeb by remember { mutableStateOf(false) }

    var expandedExperience by remember { mutableStateOf(false) }


    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    val experienceOptions = listOf(
        stringResource(id = R.string.exp_beginner),
        stringResource(id = R.string.exp_intermediate),
        stringResource(id = R.string.exp_expert)
    )


    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        textTglLahir = formatter.format(Date(millis))
                    }
                    showDatePicker = false
                }) {
                    Text("Pilih")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Batal")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Image(
                painter = painterResource(id = R.drawable.kalimantan),
                contentDescription = stringResource(id = R.string.cd_background),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.4f)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.box_top_margin))
                    .background(
                        color = colorResource(id = R.color.white_background),
                        shape = RoundedCornerShape(
                            topStart = dimensionResource(id = R.dimen.corner_radius_box),
                            topEnd = dimensionResource(id = R.dimen.corner_radius_box)
                        )
                    )
                    .padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.form_title),
                        fontSize = dimensionResource(id = R.dimen.font_title).value.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.purple_title)
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))

                    Text(
                        text = stringResource(id = R.string.form_subtitle),
                        fontSize = dimensionResource(id = R.dimen.font_subtitle).value.sp,
                        color = colorResource(id = R.color.gray_subtitle)
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_large)))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
                    ) {
                        OutlinedTextField(
                            value = textNamaDepan,
                            onValueChange = { textNamaDepan = it },
                            label = { Text(stringResource(id = R.string.label_nama_depan)) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius_button))
                        )
                        OutlinedTextField(
                            value = textNamaBelakang,
                            onValueChange = { textNamaBelakang = it },
                            label = { Text(stringResource(id = R.string.label_nama_belakang)) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius_button))
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))

                    OutlinedTextField(
                        value = textEmail,
                        onValueChange = { textEmail = it },
                        label = { Text(stringResource(id = R.string.label_email)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius_button))
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            OutlinedTextField(
                                value = textTglLahir,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text(stringResource(id = R.string.label_tgl_lahir)) },
                                trailingIcon = {
                                    Icon(
                                        painter = painterResource(id = android.R.drawable.ic_menu_my_calendar),
                                        contentDescription = stringResource(id = R.string.cd_calendar)
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius_button))
                            )
                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .clickable { showDatePicker = true }
                            )
                        }


                        ExposedDropdownMenuBox(
                            expanded = expandedExperience,
                            onExpandedChange = { expandedExperience = it },
                            modifier = Modifier.weight(1f)
                        ) {
                            OutlinedTextField(
                                value = textExperience,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text(stringResource(id = R.string.label_experience)) },
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedExperience)
                                },
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius_button))
                            )

                            ExposedDropdownMenu(
                                expanded = expandedExperience,
                                onDismissRequest = { expandedExperience = false }
                            ) {
                                experienceOptions.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        text = { Text(selectionOption) },
                                        onClick = {
                                            textExperience = selectionOption
                                            expandedExperience = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_medium)))

                    Text(
                        text = stringResource(id = R.string.label_pilih_skill),
                        fontWeight = FontWeight.Bold,
                        fontSize = dimensionResource(id = R.dimen.font_body).value.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = checkAndroid, onCheckedChange = { checkAndroid = it })
                            Text(stringResource(id = R.string.skill_android), fontSize = dimensionResource(id = R.dimen.font_body).value.sp)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = checkUiUx, onCheckedChange = { checkUiUx = it })
                            Text(stringResource(id = R.string.skill_uiux), fontSize = dimensionResource(id = R.dimen.font_body).value.sp)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = checkWeb, onCheckedChange = { checkWeb = it })
                            Text(stringResource(id = R.string.skill_web), fontSize = dimensionResource(id = R.dimen.font_body).value.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_large)))

