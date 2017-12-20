# Ability-Tutorial
Learn to create your very own ProjectKorra add-on!

# Starting Out
Before anything, you'll need to create a new JavaProject using whatever your preferred IDE is. Once you've created your JavaProject, you need to add whatever versions of ProjectKorra and Spigot your server is using as an ExternalJar. Do this by right-clicking your project and going to Properties, then clicking JavaBuildPath. You should be able to find the Add External Jars button pretty quickly after that. Once you've done this, you now have the Spigot and ProjectKorra API at your finger tips.

Click on "src/me/xnuminousx/tutorial" above and sift through the different files to learn!

# Files
* AbilityClass - Explains what goes in an ability class. This class will contain what the move actually does; as well as config variables, permissions, load messages, names, descriptions, and more!
> * Note: I recommend starting from the AbilityListener then moving on to the AbilityClass.
* AbilityListener - This will contain the event that triggers the AbilityClass. For instance, the trigger of this ability is a left-click. So when a player clicks, the AbilityClass will be triggered!

# Things To Know
* remove() - This is self explanatory really. This method will remove the current task of your ProjectKorra ability.
* BendingPlayer() - We use this method in the AbilityListener to create a player in the ProjectKorra database. Using this, we get the information of a player such as elements, abilities, and other things.
* GeneralMethods() - This is a very useful class among the ProjectKorra arsenal. Inside of this, there are many useful methods that we can manipulate. For example: In the AbilityClass we use "GeneralMethods.getEntitiesAroundPoint" The ProjectKorra developers added this feature so that addon developers could efficiently check for entities wherever we desire. You can decompile ProjectKorra, go to GitHub, or go to JavaDocs to see all that GeneralMethods has to offer you.
* ParticleEffects - ProjectKorra has a particle effect library already in their code when you add PK as an external jar. This will make using particle effects way easier!
