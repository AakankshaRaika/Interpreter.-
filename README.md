# Interpreter.-
This was actually a project I made for one of my classes. 

Functionality  
Your interpreter should read in expressions from the input file named “input.txt”, maintain a stack when running, and output the content of the stack to an output file named “output.txt” when stops. It should be able to handle the following expressions for this Program:  

1.  push   push _num_   where _num_ is an integer possibly with a ‘-’ suggesting a negative value. Here, ‘-0’ should be regarded as ‘0’. Entering this expression will simply push _num_ onto the stack. 
                     For example, push 5 0   push -0    5      

If _num_ is not an integer, only push the error literal (:error:) onto the stack instead of pushing _num_. 
                     For example,   push 5 :error:   push 2.5    5                

Pushing strings to stack: 	 push _string_literal_   where _string_literal_  consists of a sequence of characters enclosed in double quotation marks, as in “this is a string”. Entering this expression, would push the string onto the stack for example,                               batman  deadpool push “deadpool”		 push “batman”    
You can assume that the string value would always be legal i.e double quotes will not appear inside a string.    Pushing names to stack:    push _name_   where _name_  consists of a sequence of letters and digits, starting with a letter.    
                      13 a  a push a push 13     3 name1   name1    push name1 push 3    
To bind ‘a’ to the value 13 and name1 to the value 3, we will use ‘bind’ operation which we will see later (section 18)  You can assume that name will not contain any illegal tokens – no commas, quotation marks etc. It will always be a sequence of letters and digits starting with a letter.    




2.	pop   pop   Remove the top value from the stack. If the stack is empty, an error literal (:error:) will be pushed onto the stack.  
                     For example, push 5                      :error:   pop            5           pop               





3.	boolean   :true:   :false:   There are two kinds of boolean literals: :true: and :false:. Your interpreter should push the corresponding value onto the stack. 
                     For example,   push 5 :true:     :true: 5       




4.	error   :error:   Similar with boolean literals, entering error literal will push :error: onto the stack.    




5.	add   add   add refers to integer addition. Since this is a binary operator, it consumes the top two values in the stack, calculate sum and push the result back to the stack. If one of the following cases occurs, which means there is an error, any values popped out from the stack should be pushed back in the same order, then a value :error: should also be pushed onto the stack: not all top two values are integer numbers  only one value in the stack  stack is empty 
		For example,   push 5 8       push 8        5     13   add              
		For another example, if there is only one number in the stack and we use add, an error will occur. Then 5 should be pushed back as well as :error: push 5      :error:   add        5     5                




6.	sub   sub   sub refers to integer subtraction. It is a binary operator and works in the following way:   if top two elements in the stack are integer numbers, pop the top element(y) and the next element(x), subtract y from x, and push the result x-y back onto the stack    if the top two elements in the stack are not all integer numbers, push them back in the same order and push :error: onto the stack     !3   if there is only one element in the stack, push it back and push :error: onto the stack    if the stack is empty, push :error: onto the stack  For example, push 5 8        push 8         5      -3   sub               For another example, if one of the top two values in the stack is not a numeric number when sub is used, an error will occur. Then 5 and :false: should be pushed back as well as :error:   push 5           :error:                   :false:     :false:   :false:                      sub 5     5 5 




7.	mul   mul   mul refers to integer multiplication. It is a binary operator and works in the following way:   if top two elements in the stack are integer numbers, pop the top element(y) and the next element(x), multiply x by y, and push the result x*y back onto the stack    if the top two elements in the stack are not all integer numbers, push them back in the same order and push :error: onto the stack  if there is only one element in the stack, push it back and push :error: onto the stack  if the stack is empty, push :error: onto the stack  For example, push 5 8       push 8        5   40   mul     For another example, if the stack empty when mul is used, an error will occur. Then :error: should be pushed onto the stack:   mul       :error:              




8.	div   div   div refers to integer division. It is a binary operator and works in the following way:   if top two elements in the stack are integer numbers, pop the top element(y) and the next element(x), divide x by y, and push the result x\y back onto the stack    if top two elements in the stack are integer numbers but y equals to 0, push them back in the same order and push :error: onto the stack    if the top two elements in the stack are not all integer numbers, push them back in the same order and push :error: onto the stack  if there is only one element in the stack, push it back and push :error: onto the stack  if the stack is empty, push :error: onto the stack        !4   For example, push 5 8       push 8        5   0   div     For another example, if the top element in the stack equals to 0, there will be an error if div is used. Then   5 and 0 should be pushed back as well as :error:              :error:          push 5        0    0   push 0         5      5   div             




9.	rem   rem   rem refers to the remainder of integer division. It is a binary operator and works in the following way:   if top two elements in the stack are integer numbers, pop the top element(y) and the next element(x), calculate the remainder of x\y, and push the result back onto the stack    if top two elements in the stack are integer numbers but y equals to 0, push them back in the same order and push :error: onto the stack  if the top two elements in the stack are not all integer numbers, push them back and push :error: onto the stack    if there is only one element in the stack, push it back and push :error: onto the stack  if the stack is empty, push :error: onto the stack  For example, push 5 8        push 8         5      5   rem              For another example, if one of the top two elements in the stack is not an integer, an error will occur if rem is used. Then 5 and :false: should be pushed back as well as :error: push 5        :error:   :false:          :false:       :false:   rem               5       5                  




10. neg   neg   neg is to calculate the negation of an integer (negation of 0 should still be 0). It is unary therefore consumes only the top element from the stack, calculate its negation and push the result back. A value :error: will be pushed onto the stack if: the top element is not an integer, push the top element back and push :error:  the stack is empty, push :error: onto the stack  For example, push 5     neg 5 -5            !5   For another example, if the top value is not an integer, when neg is used, it should be pushed back as well as :error: push 5                    :error:   neg                    :true:     :true:    :true:        neg             -5     -5   -5       




11. swap   swap   swap interchanges the top two elements in the stack, meaning that the first element becomes the second and the second becomes the first. A value :error: will be pushed onto the stack if: there is only one element in the stack, push the element back and push :error:  the stack is empty, push :error: onto the stack  For example, push 5      :false: 8   push 8            8    8     :false:   :false:                  swap 5 5 5    For another example, if there is only one element in the stack when swap is used, an error will occur   and :error: should be pushed onto the stack. Now we have two elements in the stack (5 and :error:), therefore the second swap will interchange the two elements:   push 5                   :error:      5   swap                       swap 5      5      :error:                




12. quit   quit   quit causes the interpreter to stop. Then the whole stack should be printed out to an output file, named as “output.txt”.    




13. and   and   and performs the logical conjunction of the top two elements in the stack and pushes the result (a single value) onto the stack.    :error: will be pushed onto the stack if: there is only one element in the stack, push the element back and push :error:  the stack is empty, push :error: onto the stack  if either of the top two elements aren’t Boolean, push back the elements and push :error:   For example,   :false:  :false: :true: :  :true:    	:true: :false: and   :error:  :true:  :true: Another example, 	 :true: and	       




14. or   or   or performs the logical disjunction of the top two elements in the stack and pushes the result (a single value)onto the stack.    :error: will be pushed onto the stack if: there is only one element in the stack, push the element back and push :error:  the stack is empty, push :error: onto the stack  if either of the top two elements aren’t Boolean, push back the elements and push :error:   For example,  :true:   :true: :false: :true: :true :true: :false: or      :error: Khaleesi :false:   Khaleesi :false:  :false:   :false: push “Khaleesi” or           




15. not   not   not performs the logical negation of the top element in the stack and pushes the result (a single value)onto the stack. Since the operator is unary, it only consumes the top value from the stack.    :error: will be pushed onto the stack if: the stack is empty, push :error: onto the stack  if the top element isn’t Boolean, push back the element and push :error:   For example,  :false:  :true:   :true: not     :error: 3  3 push 3    not       




16. equal   equal   equal refers to numeric equality ( so you are not supporting string comparisons) This operator consumes the top two values on the stack and pushes the result(a single boolean value) onto the stack.    :error: will be pushed onto the stack if: there is only one element in the stack, push the element back and push :error:  the stack is empty, push :error: onto the stack  if either of the top two elements aren’t integers, push back the elements and push :error:   For example,  :true: 7 7  7   push 7 push 7 equal :error: :error: 8   :error: 8  8 push 8 push 9.5 equal          




17. lessThan   lessThan   lessThan refers to numeric less than ordering. This operator consumes the top two values on the stack and pushes the result(a single Boolean value) onto the stack.    :error: will be pushed onto the stack if: there is only one element in the stack, push the element back and push :error:  the stack is empty, push :error: onto the stack  if either of the top two elements aren’t integers, push back the elements and push :error:   :true: 8 7  7 For example,   push 7 push 8  lessThan       




18.bind bind   bind binds a name to a value. It is evaluated by popping two values from the stack. The second value popped must be a name (see section on push for details on what constitutes a ‘name’). The name is bound to the value (the first thing popped off the stack). The value can be any of the following : An integer  A string  Boolean  :unit:  The value of a name that has been previously bound   The name value binding is stored in an environment data structure. The result of a bind operation is :unit: which is pushed onto the stack.   Modification 3/26/16   :error: will be pushed onto the stack if: the name is already bound in the current environment If we are trying to bind an identifier to an unbound identifier. the stack is empty, push :error: onto the stack  in which case all elements popped must be pushed back before pushing :error: onto the stack.    For  example :  :unit: 3 a  a   push a push 3 bind   7 sum1  sum1   sum2 :unit:  :unit: push sum1 push 7 bind 5 sum2 :unit: push sum2 :unit: :unit: push 5 bind        You can use bindings to hold values which could be later retrieved and used by functionalities you already implemented. For instance in the example below, an addition on a + name1 in example1, would add 13 + 3 and push the result 16 onto the stack.    :unit: 13 a  a push a 			 push 13 bind push name1       		 :unit: :unit: 3 name1 :unit: name1 :unit: push 3 bind push a push name1 add    16 :unit: :unit: name1 a :unit: :unit: a :unit: :unit:           While performing operations, if a name has no binding, push  :error: onto the stack.   Bindings can be overwritten, for instance:   push a  push 9 bind push a push 10 bind   Here, the second bind updates the value of ‘a’ to 10.     




19.if if   if pops three values off the stack; x,y and z. The third value popped (z, in this case) must always be a Boolean. If z is :true:, if has the value of x, and if z is :false:, if has the value y.   :error: will be pushed onto the stack if: the third value is not Boolean. the stack is empty, push :error: onto the stack  there are less than 3 values on the stack in which case all elements popped must be pushed back before pushing :error: onto the stack.   9   9 8 :true:  8 :true:   :true: :true: push 8 push 9 if                                 




20. let...end   let...end limits the scope of variables.  “let” marks the beginning of a new environment – which is basically a sequence of bindings. The result of the let..end is the last stack frame of the let. Let..end can contain any number of operations but it will always result in a stack frame that is strictly larger than the stack prior to the let.    Trying to access an element that is not in scope of the let..end block would push :error: on the stack. let..end blocks can also be nested.  			  13 c   c   a :unit:   :unit: 3 a :unit: let push c push 13 bind let push a push 3 c a :unit: :unit: 16 :unit: :unit:  :unit: :unit:  a :unit: :unit:         16 :unit:      bind push a push c add end let push b push “ron” bind end :unit: 16 :unit:  ron b 16 :unit:   :unit:        b 16 :unit:      end          (c,13)   Environment 1     (a,3) Environment 2      (b,ron) Environment 3         In the above example, the first let statement creates an empty environment (environment 1), then the name c is bound to 13. The result of this bind is a :unit: on the stack and a name value pair in the environment. The second let statement creates a second empty environment. Name a is bound here. To add a and c, these names are first looked up for their values in the current environment. If the value isn’t found in the current environment, it is searched in the outer environment. Here, c is found from environment 1. The sum is pushed to the stack. A third environment is created with one binding ‘b’.The second last end is to end the scope of environment 3 and the last end statement is to end the scope of environment 1.     You can assume that the stack is left with at least 1 item after the execution of any let..end block.
