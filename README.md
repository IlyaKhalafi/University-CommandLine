# University CommandLine

Simple university manager in commandline.

## Supported Commands

1. Adding Student :
> add student \<name> \<family> [-y \<year>]

2. Listing Students :
> list student [-p \<prefix>]

3. Calculating Student's Tuition :
> tuition student \<number> \<sem>

4. Adding Professor :
> add prof <username> <name> <family>

5. Calculating Professor's Salary :
> salary prof <username> [-s \<sem1> \<sem2> \<sem3> ...]

6. Listing Professors :
> list prof

7. Calculating University's Profit :
> profit \<sem>

8. Adding Course :
> add course \<name> \<username> \<sem> [-w \<weight>]

9. Listing Courses :
> course list [-p \<username>] [-s \<sem>]

10. Assinging Course to Student :
> assign course \<name>:\<username>:\<sem> \<number>

11. Listing Student's Courses :
> show course \<number> \<sem>

12. Removing Student's Course :
> remove course \<name>:\<username>:\<sem> \<number>

13. Assinging Grade to Student for a Course :
> assign grade \<name>:\<username>:\<sem> \<number> \<grade>

14. Calculating Student's Average :
> average \<number> \<sem>

15. Listing Ranks (Top 10) :
> ranks \<sem>


## TODO
- Saving data in database
