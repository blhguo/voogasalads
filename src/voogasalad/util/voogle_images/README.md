![alt text](https://imgur.com/sQcXNmJ.png)
========
Voogle Images is a utility that allows users of a game authoring environment to browse for images online that they can then use within their game. This prevents users from being limited to default images or having to add images manually to the project if they are unsatisfied with those default choices.

It was written by Ben Hubsch on team Call Us Salad Cuz We Out Here Dressin'.

## Integration

Voogle Images is extremely simple to use and was built with integration convenience in mind. Here's how to include it in your VOOGASalad project:

1. Have one of your current classes implement the `ImageObserver` interface. That class will need to implement the `void update(File file)` method, which is the method that will be called whenever a user downloads an image. You may choose to render that image immediately or do nothing with it and simply allow it to be saved to your local machine.
2. Instantiate a `VoogleImages` object, and inject a reference to the class that implements the `ImageObserver` interface into the constructor. 
3. Whenever you want to launch the `VoogleImages` utility, call its `void go()` method. I would expect this to most likely come from a button click, i.e. `button.setOnAction(e -> myVoogleImages.go());`, but that's completely implementation dependent and up to you.

That's all it takes to get this utility up and running. An example JavaFX application can be found in the `example/` directory, and I wholeheartedly encourage you to check that out to see how it might be used. It may be particularly useful for figuring out how to convert `File` objects to `ImageView` objects.

**Notes:** 

1. Currently, the default save directory that the `FileChooser` opens to is your project's highest level directory. If you would like to customize this so that images are saved to some other directory, you may do so by editing the `DEFAULT_SAVE_DIR` constant inside of `VoogleImages.java`.
2. Occasionally, Voogle Images is unable to recognize URLs as being images. The currently supported image formats are .jpg, .tif, .tiff, .bmp, .gif, .png, .jpeg, and .wbmp. I'd estimate that one out of every forty images on Yahoo! has a different extension and won't be recognized by Voogle Images. In the event this happens, you'll have to navigate to find a different image to suit your needs.

## Use

1. Once Voogle Images has been launched, it should be fairly straightforward to use. You will be greeted by a search home page, where you can type in the name of the object you are searching for. ![alt text](https://imgur.com/xjY4eS9.png)

2. Once you've entered a search, you can scroll up and down the results page looking for just the right image. ![alt text](https://imgur.com/7P3VLhw.png)

3. You can then select the image by clicking on it, which will enlarge it into the view you see below. ![alt text](https://imgur.com/2KTfZSW.png)

4.  Clicking the "View Image" button will bring you to a screen containing nothing more than the image you are interested in downloading. You'll notice that the button in the upper right-hand corner of the application has changed from "Non-image URL" to "Download." This gives you the green light that Voogle Images recognizes the URL as an image and should be able to complete the download for you successfully. ![alt text](https://imgur.com/1Z6nq5m.png)

5. After downloading an image by giving it a name and a directory to be saved in, a download bar will pop up at the bottom of the screen. It functions similarly to the Google Chrome downloads bar, allowing you to see your past downloads. If you click on the gray portion of the download, it will open that image on your computer in your native image viewing application (on a Mac, this is Preview). If you click the red "X," that download will disappear. ![alt text](https://imgur.com/m3tlhhK.png)

At any point over the course of the lifetime of the application, you may use the "Back," "Next," and "Search" buttons to navigate around. "Back" and "Next" do exactly what you would expect, and "Search" returns you to the default home page (which is Yahoo! Images by default).


## Contact

I hope you find this utility useful! Let me know if you have any questions or comments at bah37@duke.edu. Happy coding!