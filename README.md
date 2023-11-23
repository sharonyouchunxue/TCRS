# TCRS
Copyright © 2020 by Dr. Simon Xu, Sault Ste Marie, Ontario
Al rights reserved. No part of this work may be reproduced or used in any form or by any means –
graphic, electronic or mechanical – without the prior written permission of the author.
COSC/ITEC 3506 Projects 
General 
A major component of the course is a software development project, which you will be 
required to work on throughout the course. The aim of the project is to let you experience 
several facets of software engineering that will help you appreciate the topics covered in 
COSC3506/ITEC3506, and will hopefully help you to participate effectively in realworld projects in the future. 
For this purpose, during the first two modules of the course, we will form project teams 
with 4 members. During the semester, the project team will work together through the
full development cycle from understanding the requirements to delivering a functioning 
product and making a presentation of your work to the "client". 
In the following sections, you will find information about starting up your project, the 
software engineering environment as well as the project assessment scheme. 
Starting up the project 
The first thing to do is to read the project descriptions at the end of this document and 
select your preferred project(s). During the first two modules, you will be assigned to a 
team and a project. The information you provide will greatly assist us in forming project 
teams and assigning them to projects. Despite all the efforts, however, there is no 
guarantee that you will get your most preferred project or people to work with. 
A member from each team will be appointed contact person. They will initially make 
contact with the instructor. The team may choose to have a democratic organization, but 
they should still have a formal contact person. You can also choose a team leader. The 
list of teams along with their assigned contact people and projects will be posted on the 
web. 
It is important that you start working on your project as early as possible. Delays, even at 
the start, can cause problems when deadlines approach. 
Project Meetings 
You are required to hold semi-weekly team meetings. This is where you discuss 
managerial and technical issues, conduct reviews and so on. The team leader or the 
contact person should prepare an agenda before the meeting, chair the meeting, arrange 
someone taking minutes, and should circulate the minutes after the meeting. 
The main lab area is generally not to be used for project meetings unless they involve 
only 2-3 people who can discuss things without disturbing other students there. 
Copyright © 2020 by Dr. Simon Xu, Sault Ste Marie, Ontario
Al rights reserved. No part of this work may be reproduced or used in any form or by any means –
graphic, electronic or mechanical – without the prior written permission of the author.
Projects
The projects will only concern with Classic stream using a traditional, document-based 
waterfall methodology. 
As part of the project work, each team must produce and hand in the following 
documents by the given dates. These deadlines must be kept strictly (there is simply not 
enough time to catch up later if you get behind schedule, and the completion of the 
project will be in danger). 
Document name Deadline -> Refer LMS
D1 Project Plan (do not hand in) XXX
D2: Requirements and specification XXX
D3: Architecture and User Interface Design XXX
D4: Detailed Design XXX
D5: Implementation XXX 
D6: Testing XXX
Each document is to have an editor who has the final responsibility for turning in the 
document, sets deadlines for what the other members are to write, and may have to apply 
pressure on the other team members to do their part. Each team should prepare for a 
binder.
Assessment of project
As software development is rarely an individual effort these days, you have to learn the 
basic skills of working in teams in order to participate successfully in real software 
engineering projects. We attempt to create an environment that reflects real-life situations 
to a large extent as well as stimulates cooperation among team members. While software 
development is a team effort, we also want to acknowledge the fact that individual 
differences do exist. 
As mentioned in the syllabus, a significant part of your final grade comes from your 
project mark. The project mark has two components: the group mark and the individual 
modifier. The group mark is how well the project went overall, and will be made up from 
marks for the individual documents and other related items. Each time when a report is 
handed in, a Time Recording Log sheet, Project Time/Effort Summary for each team 
member should be included. 
Copyright © 2020 by Dr. Simon Xu, Sault Ste Marie, Ontario
Al rights reserved. No part of this work may be reproduced or used in any form or by any means –
graphic, electronic or mechanical – without the prior written permission of the author.
Project Plan (D1) 00%
Requirements and specification (D2) 15% 
User Interface Design (D3) 05%
Detailed design (D4) 25% 
Deliverable (D5) 40%
Testing (D6) 10%
Progress presentation 05%
Note: As group project work is a one single assessment (however, required to be submitted 
in multiple parts) would be evaluated as per the above weight-age as a single assessment at 
the end of the term.
Technical environment
Projects will be implemented using C++/Java for Windows. 
Project Progress Presentation and Final Demonstration
There will be one project progress presentation and one final demonstration. The progress 
presentation will be hold in XXX, which worth 5% of your project. Towards the last 
week (probably during the last week, but possibly earlier), each team will demonstrate 
the program they have developed to the "client(s)". These should be geared towards 
"selling" the product. These presentations not only are assessed, but also the products on 
which they are based will be assessed. 
Project Descriptions
Project 1：Traffic Citation and Reporting System
Some governmental agency at the provincial level has the responsibility for assembling statewide information about traffic citations and disseminating it upon request by an appropriate 
agency such as a sheriff's office or the highway patrol. This same agency also maintains 
information about vehicles licensed and registered within the province. When an officer makes a 
traffic stop, s/he wants to be able to ask a dispatcher to query the central agency to determine the 
status of a vehicle (for instance, registered? stolen? wanted for some reason?), the status of the 
driver (license suspended? revoked? outstanding warrants?), and the driving record of a particular 
driver.
A governmental agency at some local level is involved in peace-keeping efforts which include 
jurisdiction over traffic codes. Therefore, officers of this agency can issue citations for parking 
violations (issued to a particular vehicle), moving vehicle code violations (issued to a particular 
driver), moving vehicle code warnings (issued to a particular driver), fix-it tickets (issued to a 
vehicle), etc. This agency needs to be able to report the number and category of citations issued 
by a particular officer in a particular time period, the names of individuals for whom arrest 
warrants are in effect because they have not paid their citation fine or been found innocent of the 
violation, the license numbers of vehicles with outstanding (unpaid) parking or fix-it tickets, and 
similar related information. This agency also reports moving vehicle citations to the state agency, 
Components Weight 
Copyright © 2020 by Dr. Simon Xu, Sault Ste Marie, Ontario
Al rights reserved. No part of this work may be reproduced or used in any form or by any means –
graphic, electronic or mechanical – without the prior written permission of the author.
and also reports warrants for particular vehicles (for instance, for unpaid parking fines, for being 
reported stolen, for being reported involved in the commission of some crime, etc.) 
Sometimes, in addition to paying a fine for a moving vehicle violation, an offender can pay to 
attend traffic school, which has the effect of preventing the reporting of the citation to the central 
agency. Traffic school requires that an offender attend eight hours, which can be scheduled for an 
eight-hour day, or four two-hour periods in the evening, or any other way the agency chooses to 
schedule the sessions. If an individual chooses to attend traffic school, s/he must register for a 
particular session and attend the entirety of that session, however it is scheduled, in order for 
his/her citation to be non-reported.
Project 2: Local Area Network Office Messaging System:
Design an office messaging system, like a compact messenger system, which will allow staff 
members to send messages to each other that can be retained, deleted, archived, and organized by 
both the sender and receiver. The system can function on a peer-to-peer basis, or through a 
centralized message server. It will utilize the office LAN – ensuring that messages do not leave 
the office environment. Individuals can be assigned to one or more groups, allowing messages to 
be sent to a single individual, several individuals, one or more groups, or broadcast to the entire 
office. 
As a minimum, the messaging system should provide the following features:
• Identify all members of the messaging system
• Indicate the status of each member (ie. Offline or Connected)
• Ability of the user to set individual status (ie, online, away, busy, etc…)
• Provide a facility for the creation of short messages (you must decide on the feasible size)
• Display a pop-up message to recipient upon arrival of message
• Store all incoming messages in message Inbox.
• Store copies of all sent messages
• Once read, messages can be retained or removed from the Inbox.
• Backup and restore capabilities
Some additional features of the system which should be considered in it design might be:
• Sound notification option when a new message arrives
• More advanced status settings such as: Online, Busy, Out to Lunch, On the Phone, Away
from Desk
▪ Deferred messaging to offline users that will be delivered when the recipient comes
online.
• Creation of groups to which users can be assigned to facilitate group messaging
• Office wide message broadcasting that can be directed to all users or just users online.
• Out-of-Office return messages providing details such as “at meeting ABC until…”
▪ Long-term message archive where messages are removed from the primary storage to a
secondary archive for long-term storage
▪ Categorization of messages based on Projects, Clients, or other Users.
Please note: this is not intended to be a chat system but rather a short message delivery system. 
In an office, chatting can be more efficiently handled using the office phone system or simply 
Copyright © 2020 by Dr. Simon Xu, Sault Ste Marie, Ontario
Al rights reserved. No part of this work may be reproduced or used in any form or by any means –
graphic, electronic or mechanical – without the prior written permission of the author.
meeting at the water cooler.
Project 3：COSC Club Event Management System
An application that allows the COSC Club Management Team (CCMT) to perform and 
track all the tasks and functions necessary to meet their responsibilities as the governing 
body of undergraduate students. CCMT will be able to create events, manage 
membership, and get up to the minute financial information for their club. The system 
will be able to advertise events via email and provide tracking for event attendance and 
expenditures. The application will allow the CCMT to create reports detailing club and 
event activity to assist in their interactions with the department for budgeting concerns.
The scope of this project will be to provide a system that allows the CCMT to:
1. manage undergraduate clubs
2. manage undergraduate events
3. manage financial transactions of these undergraduate clubs
4. manage system users
You can think additional functionalities and add them to the list.
Project 4：Employment Application Review System
EARS is an intranet-based Employment Application Review System for the Department 
of Math and Computer Science in Algoma University. The system is designed so that 
department faculty members can review applicants and collaborate asynchronously in 
order to find the best applicant for a given job opening. This system reduces the overhead 
of the process and lightens the workload for the search chairperson.
The scope of this project will be to provide a system that allows the CCMT to:
1. log-in EARS system
2. manage system users (add new accounts)
3. add a new faculty search (committee chair, members, position,
search starting date and ending date, add new committee members)
4. List and review applications (view profile, post comments on
applicants, change applicants’ statues, perform a faculty review, assign faculty review)
5. set account’s settings (email, name, title, password)
