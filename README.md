# AndroidMVCKotlinDemo

#Clone the Project:

URL: https://github.com/Hstory/app.git

# Structure
Activity, Fragment
Activities are containers for Fragments. Fragments handle lifecycle of views. After Views creation, they are subscribed to listen for ScreenState changes. Likewise, before Views destruction, they are unsubscribed to prevent memory leaks.

Model
Every model is stored in singleton called Storage. It provides basic methods to get item with id, write it, or update. There are two types of items:

ScreenState - which represents current state of a screen (information like: is progress bar visible?; is animation running?; what's scroll position?) and holds list of ItemModels ids.
ItemModel - contains data used for filling one view. When model changes, it notifies corresponding ScreenStates to update themselves.

View
Layout - Group of Views. updates itselve with data from ScreenState. Passes ItemModels ids to its children.
View - after update trigger, it gets ItemModel from Storage, and displays the data.

Controller
Each controller contains list of static methods used for changing models inside Storage