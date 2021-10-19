# README
To build use:
`mvn package`

To run use:
`./run`

To start the server use:
`./run --gui [--port=<port>]`

To use this code, simply run the main method to begin the REPL.

## Commands
###recsys_load responses
**Description**: loads all student data from API & ORM into a hashmap of Student objects. 

**Returns**: "Loaded Recommender with k students"

###recsys_rec <num_recs> <student_id>

**Description**: Generates team recommendations for a student where student is determined by their student_id and the team size determined by num_recs.

**Returns**: A list of IDs corresponding to the recommendation, in order of preference to the input student.

<matched_student_id_1>
<matched_student_id_2>
...
<matched_student_id_k>

###recsys_gen_groups <team_size>

**Description**: Generates recommended teams of size _team_size_, and leftover students are placed in a random team.

**Returns**: A list of IDs representing each student in each team, in order of preference.

[group_1_student_1, group_1_student_2, … group_1_student_k]
[group_2_student_1, group_2_student_2, … group_2_student_k]
...
[group_n_student_1, group_n_student_2, … group_n_student_k]

## Methodology
###Data and Preferences
Data was taken from two sources: the integration API and integration SQLite.

- **IntegrationJSONOpener** handles calling the API and then creating Student objects with the API half of the data.
- **IntegrationORMOpener** handles querying the SQLite DB and then adding the second half of the data to those previously created Student objects.
- This happens in **RecsysLoadResponses**, which implements **CommandHandler**

We decided to handle preferences with mostly matching similar traits, and sometimes matching inverse traits.

****
