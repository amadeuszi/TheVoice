#Battle of voices
####A project for Object Oriented Programming on my University
The app is checking which singers have the best linguistic skills. It counts the amount of distinct words that are used by 
particular artists. Class Count is responsible for that. It also can tell you what 5 words are mostly used by a particular artist. 
Class Top5 implements these feature. You can also use some filters to ignore words that are bound to win such as 'you', 'love' 'oh', or some pronouns.
As input you have to prepare directories, in every directory there have to be a lot of song lyrics for ona artist. The name of the directory
has to be the name of the artist that the directory represents.  
Source of the program has to be given directly when opening the program.
####For example:  
`java TheVoice --source=./ --processors=top5,count --filters=./filter1.txt:./pronouns.txt Madonna Boney_M`  
* `--source=` After that you have to give the path to the directory, in which there are located directories representing artists  
* `--processors=` you decide what program has to do  
  `count` means that it should count how many distinct words given artists use. The results are sorted so you can see who has the best linguistic skills.
  `top5` means that it should find the most frequently used 5 words.
* `--filters=` you give the paths to files of words that will be ignored, files have to be separated by ':'  
* After that you have to name what artists should be processed. In the source folder there should be directories named like these artists, but instead of '_' you should use space
in the name of directory. So in our example there should be at least two directories "Boney M" and "Madonna"

