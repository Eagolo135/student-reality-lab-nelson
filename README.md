# Student Reality Lab

An Android data-story app built with Kotlin, Jetpack Compose, Material 3, and MPAndroidChart.

## Essential Question
Where is the middle-class income line today after adjusting historical incomes for inflation?

## What the App Does
- Loads a local JSON dataset of income + CPI values by year.
- Plots two line series:
  - Median household income (nominal)
  - Inflation-adjusted income (to latest-year dollars)
- Provides a year slider interaction to inspect adjusted values per year.
- Displays a callout insight (example: purchasing power of $100,000 from 1990 in today’s dollars).

## Tech Stack
- Kotlin
- Android Jetpack (Compose, ViewModel, Lifecycle)
- Material 3
- MPAndroidChart
- Kotlinx Serialization (JSON)

## Project Structure
- `app/src/main/java/com/studentrealitylab/app/data/`
- `app/src/main/java/com/studentrealitylab/app/models/`
- `app/src/main/java/com/studentrealitylab/app/ui/`
- `app/src/main/java/com/studentrealitylab/app/chart/`
- `app/src/main/java/com/studentrealitylab/app/utils/`

## Inflation Adjustment Logic
The app uses CPI ratio scaling:

`adjustedAmount = historicalAmount * (targetCpi / historicalCpi)`

Implemented in `InflationCalculator`.

## Run in Android Studio
1. Open this folder in Android Studio.
2. Let Gradle sync finish.
3. Run the `app` configuration on an emulator/device (API 26+).

## Dataset
- File: `app/src/main/assets/income_data.json`
- Fields:
  - `year`
  - `median_income`
  - `cpi_index`
  - `real_income` (optional, falls back to computed value)

## Test
A unit test validates CPI-ratio math:
- `app/src/test/java/com/studentrealitylab/app/utils/InflationCalculatorTest.kt`
