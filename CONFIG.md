# Configuration Tutorial
Making a configuration for an ability is pretty simple and can all be done in 1 class! What you’re going to do, is take knowledge about java you already know and then apply it to the configuration.

Essentially what you’re doing is creating a variable, then applying that variable to a config path. Here’s an example:

Let’s say I create an int variable called “thisNumber”. In this scenario, I’m going to let thisNumber represent the distance an ability can travel. If I want the distance to be configurable, first I have to apply it to a config path (which I will get to explaining in a sec). Now that “int thisNumber = [the config path]” we can use it! Only issue is, that config path doesn’t equal anything just yet, it’s empty. This is where we create what we call the default config. This config spawns in as soon as the ability is loaded (hint hint) and from there, config editors can manipulate it. Once you’ve created a default config path and set a variable equal to that config path, you’re able to use it! Now that the mechanics are explained, let’s get to the steps and specifics.

When you heart the term “config path” think of an actually pathway (like a sidewalk). This hypothetical path will lead you to your destination just like a config path will lead you to a variable.

# Steps
* The first step will be creating a variable. This step is very important as the type of variable it is will determine how we spawn the config. For this example, we will create an int variable called “range”. In the implied code, this variable will be used to determine how far our ability is allowed to travel. Your variable should look like this: 
* Now that we have a variable to use, we need to have it equal something. Sometimes, you may see it as “private int range = 10;” but because we want the number to be configurable, we are going to set it equal to a config path rather than directly to a number. Here, we are going to take advantage of a method that ProjectKorra provides us with to create the variable. First, you’ll need to access the config and it should look something like this:  Here is what each of those names mean:
>* this.range - This is the variable we’re wanting to be configurable. We’re setting it equal to a config path.
>* ConfigManager - This is the class that ProjectKorra uses to manage its configuration file.
>* getConfig - Here is where we are getting the ProjectKorra config.yml.
>* getInt - We use getInt because our variable is labeled as an integer. If our variable was boolean we’d use getBoolean, if our variable was long then we’d use getLong, if our variable was a String we’d use getString, and etc.
* Now you’ll notice in this picture:  I ended with an open parenthesis. That is because inside of it, we will use a String to determine the config path. Here is what the path looks like for the ability I’m using as an example: each category of the config is separated by periods. This is what that path would look like in the config: notice how every time it’s separated by a period, a new indentationion is made. If you study the ProjectKorra config, you’ll be able to see these indentations throughout the config. For example, the WaterManipulation config path may be “Abilities.Water.WaterManipulation.Range”.
* Now that we’ve created a variable and set it equal to a specific path in the config, we need that config path to equal something, because right now it hasn’t even been created. We’re going to move to the load() method of your ability now. Here, you will be using a very similar method to before, only a tad different. Here’s what it’ll look like: Now I’ll explain what every bit means.
>* config - I’m using an ability in Spirits as an example and because Spirits has a lot of abilities, I’ve shortened the amount of work. “Config” is my own variable that I set equal to  If you wanted, you could do it the long way and it’d end up looking like this: 
>* addDefault - This is telling ProjectKorra to add a new config path to it’s config.yml. Inside of the parenthesis is where you’ll define what the path is, and what the path equals.
>* (firstVariable, secondVariable) - The firstVariable must be a String and has to be the config path you want to use. In this example, firstVariable = Abilities.Spirits.DarkSpirit.Shackle.Range. And the second variable must be whatever you want the default to be. In this situation, we set range to be an int so we must use a whole number here. Let’s set it to 20 since that’s what Shackles’ default is. By now, you’re parenthesis should look like this: 
* So at this point, you should have:
>* Created a variable.
>* Set that variable equal to a config path.
>* Defined/Created that config path in your load() method.
>* Reloaded your server to make sure it worked.
