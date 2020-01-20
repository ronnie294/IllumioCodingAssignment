# IllumioCodingAssignment
Work done for the Illumio Data Engineer Intern Position Coding Assignment

I decided to treat a rule as an object, so that i can make a set of rules. Advantage of having a set of rules is that when a firewall starts up, i can load up a set of rules according to the CSV file. For Ports and Addresses ranges, we add a new rule for each number in the range, because this makes lookup easier.

After loading up the set of rules, when given the parameters for acceptPacket, i just create a rule for the parameters and check if it exists in a rule set.

The run time is O(n) where n is the number of rules, storing the rules in a set makes the lookup constant time.

I have created a test folder and tested my program with random inputs, My code is readable and well documented.

If given more time, i would have tested more rigorously, and cleaned up the code even more.

I have a strong preference to work in the Data team, my rankings are
1.Data
2.Platform
3.Policy
