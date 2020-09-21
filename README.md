# Smart App Rate 
<img src="https://raw.githubusercontent.com/vimalcvs/Smart-Rating-App/master/img/ic_launcher_round.png" alt="" width="100" height="auto" />

Smart app rate dialog for Android which takes user rating into consideration. If the user rates the app below the defined threshold rating, the dialog will change into a feedback form. Otherwise, It will take the user to the
Google PlayStore.

<img src="https://raw.githubusercontent.com/vimalcvs/Smart-Rating-App/master/img/2.png" alt="" width="300" height="auto" />  <img src="https://raw.githubusercontent.com/vimalcvs/Smart-Rating-App/master/img/1.png" alt="" width="300" height="auto" />

## Features
- Auto fetches the app icon to appear on top of the dialog
- Make the dialog appear on a defined app session
- Opens Feedback form if the user rates below the minimum threshold
- Extracts the accent color from your app's theme
- Customizable title, positive button and negative button texts
- Customizable button colors and backgrounds
- Override dialog redirection to Google Play or Feedback form according to your needs

If you want the dialog to appear on the Nth session of the app, just add the `session(N)` to the dialog builder method
 and move the code to the `onCreate()` method of your Activity class. The dialog will appear when the app is opened for the Nth time.

## How to use

Use the dialog as it is
```java
    RateDialogManager.showRateDialog(MainActivity.this, savedInstanceState);
```

## Installation

### build.gradle 
Add JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

### Gradle
Add it as a dependency in your app's build.gradle file

```groovy
dependencies {
      implementation 'com.github.vimalcvs:Smart-Rating-App:1.0.0'
}
```

## Credits

This project was initiated by **TechnoVimal.in**. You can contribute to this project by submitting issues or/and by forking this repo and sending a pull request.

![](https://mlsvormsouvm.i.optimole.com/DV0GLTY-FqZU1jKu/w:auto/h:auto/q:auto/https://www.technovimal.in/wp-content/uploads/2019/09/technovimal_moblie_logo_250x40-1.png)

Follow us on:

[![Facebook](http://codemybrainsout.com/files/img/fb.png)](https://www.facebook.com/vimalcvs)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[![Twitter](http://codemybrainsout.com/files/img/tw.png)](https://twitter.com/vimalvishwakar6)

Author: [Vimal K. Vishwakarma](https://github.com/vimal)

# License
```
Copyright (C) 2020 Code Mr. Vimal K. Vishwakarma

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
