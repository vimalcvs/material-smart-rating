# Material Design3 Smart Rating for Android
[![Android CI](https://github.com/prathameshmm02/ThemeEngine/actions/workflows/android.yml/badge.svg)](https://github.com/prathameshmm02/ThemeEngine/actions/workflows/android.yml)
[![](https://jitpack.io/v/vimalcvs/Material-Smart-Rating.svg)](https://jitpack.io/#vimalcvs/Material-Smart-Rating)
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Material3 Smart app rate dialog for Android which takes user rating into consideration. If the user rates the app below the defined threshold rating, the dialog will change into a feedback form. Otherwise, It will take the user to the Google Play Store.

## Screenshots
### Themes 
| Light | Dark | Rate | Feedback |
|:---:|:---:|:---:|:---:|
| <img src="gif/light.gif" width="200"/> | <img src="gif/dark.gif" width="200"/> | <img src="gif/image1.jpg" width="200"/> | <img src="gif/image2.jpg" width="200"/> |

<b>Note</b>: This library best support ```Theme.Material3.DayNight```.

## Features
- Auto fetches the app icon to appear on top of the dialog
- Make the dialog appear on a defined app session
- Opens Feedback form if the user rates below the minimum threshold
- Extracts the accent color from your app's theme
- Customizable title, positive button and negative button texts
- Customizable button colors and backgrounds
- Override dialog redirection to Google Play or Feedback form according to your needs

If you want the dialog builder method and move the code to the `setOnClickListener()` method of your Activity class.
 
## Installation

Add jitpack in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
```
Step 2. Add the dependency

```
	dependencies {
	
	        implementation 'com.github.vimalcvs:material-smart-rating:6.0.0'
	}
```

## How to use
Use the dialog as it is `setOnClickListener()` Just like <a href="https://github.com/vimalcvs/material-smart-rating/blob/master/app/src/main/java/com/vimalcvs/myapplication/MainActivity.java">MainActivity.java</a>
```java

        Button rating_app = findViewById(R.id.rating_app);
        rating_app.setOnClickListener(v -> {
	
	   //Rating
            FragmentManager fragmentManager = getSupportFragmentManager();
            MaterialRating feedBackDialog = new MaterialRating();
            feedBackDialog.show(fragmentManager, "rating");
	    
	    
        });
	
        Button feedback_app = findViewById(R.id.feedback_app);
        feedback_app.setOnClickListener(v -> {
	
	   //Feedback
            FragmentManager fragmentManager = getSupportFragmentManager();
            MaterialFeedback materialFeedback = new MaterialFeedback("technovimalin@gmail.com");
            materialFeedback.show(fragmentManager, "feedback");
	    
        });
```

## Credits

This project was initiated by **TechnoVimal.in**. You can contribute to this project by submitting issues or/and by forking this repo and sending a pull request.

![](https://mlsvormsouvm.i.optimole.com/DV0GLTY-FqZU1jKu/w:auto/h:auto/q:auto/https://www.technovimal.in/wp-content/uploads/2019/09/technovimal_moblie_logo_250x40-1.png)

Follow us on:

[![Facebook](http://codemybrainsout.com/files/img/fb.png)](https://www.facebook.com/vimalcvs)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[![Twitter](http://codemybrainsout.com/files/img/tw.png)](https://twitter.com/vimalvishwakar6)

Author: [Vimal K. Vishwakarma](https://github.com/vimalcvs)

# License
```
Copyright (C) 2022 Code Mr. Vimal K. Vishwakarma

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
