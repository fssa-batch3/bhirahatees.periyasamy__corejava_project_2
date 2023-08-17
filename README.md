# sec_a_bhirahatees.periyasamy__corejava_project_2

# Pupdesk


### [Milestone](https://github.com/fssa-batch3/sec_a_bhirahatees.periyasamy__corejava_project_2/milestone/1)

## ER Diagram

[![HDDPQgn.md.png](https://iili.io/HDDPQgn.md.png)](https://freeimage.host/i/HDDPQgn)

## Flow

 ````mermaid  
flowchart TD  
A[Start Registration]  
A --> B[User provides email and password]  
B --> C[Validate email format]  
C -->|Valid| D[Check if email is already registered]  
C -->|Invalid| B  
D -->|Already Registered| E[Notify user: Email already in use]  
D -->|Not Registered| F[Proceed with registration]  
F --> G[Collect additional user information]  
G --> H[Validate user information]  
H -->|Valid| I[Create user account]  
H -->|Invalid| G  
I --> J[Send verification email]  
J --> K[Wait for user to verify email]  
K -->|Verified| L[Registration Complete]  
K -->|Not Verified| K  
F[Finish] 


