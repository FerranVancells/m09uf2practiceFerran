# M09 UF3 Practice
## Project description

Console application to create file hashes

## Project structure

The main class is called Program. When run, it 
* loads the hashing configuration parameters from the file _hash.properties_, then
*  asks for the file path and
* shows the calculated digest and the salt.
* Finally, it asks whether you want to hash another file or exit the program

## Utilities
* _SaltGenerator_: Run this class if to generate *salt.txt* with some random salts in base64

## TODO

Check every minute if the file _hash.properties_has changed. If so update the hashing configuration 

## Versions

| Version | Changes              |
|---------|----------------------|
| 1.0.0   | Main functionalities |
