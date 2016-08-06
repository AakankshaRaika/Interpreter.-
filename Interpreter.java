import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class hw_3 {

public enum pushType {NUM, STRING ,NAME;}

	//THIS IS PART ONE 1-12 OF THE HOMEWORK ON JAVA AND IS NOW COMPLETED. CONVERT THE SAME LOGIC INTO PYTHON BY 20TH OF JULY.
	public static boolean isNumeric(String str)
	{
		for (int index = 0 ; index <= str.length()-1; index++){
			char c = str.charAt(index);
			if (!Character.isDigit(c)) return false;
		}
		return true;
	}
	public static Stack<Object> push_num_(String x,Stack<Object> myStack,Map<Object, pushType> myMap){
		String number = "" ;
		for (int index = 5; index <= x.length()-1 ; index++){ //the reason it is index 4 is push 
			number += x.charAt(index);   
			if (x.charAt(index) == '-' && index == 5){
				index = index;
			}
			else if(x.charAt(5) >= 'a'&& x.charAt(5) <= 'z'|x.charAt(5) >= 'A'&& x.charAt(5) <= 'Z'|x.charAt(5) >= 'A'&& x.charAt(5) <= 'z'){              //checking for the first thing after push be a letter 
				String stringInput = x.substring(5, x.length()); 
				myStack.push(stringInput);
				index = x.length();
				myMap.put(stringInput, pushType.NAME);

			}
			//parses through the string and collects the number.  
			else if(index == x.length()-1 && (isNumeric(number)|isNumeric(number.substring(1,number.length())))){
				int result = Integer.parseInt(number,10);  //converts string to int. 
				if (x.charAt(5)==('-')){
					result = -result; 
				      myStack.push(result);
				}
				myStack.push(result);
				
				myMap.put(result,pushType.NUM);//Pushes the number on to the stack. 
			}
			else if (index != x.length()-1 && (isNumeric(number)|isNumeric(number.substring(1,number.length())))){
				index = index;
			}

			else if(x.charAt(5)== '\"' && x.charAt(x.length()-1)== '\"' ){
				String stringInput = x.substring(6, x.length()-1); 
				int indexString =0;
				while(indexString < stringInput.length()){
					if(stringInput.charAt(indexString)== '\"'){
						myStack.push(":error:");
					}
					indexString++;
				}
				myStack.push(stringInput);
				index = x.length();
				myMap.put(stringInput, pushType.STRING);
			}
			
			else  {
				myStack.push(":error:");
			}
		}	
		return myStack; 
	} // COMPLETED PUSH_NUM_

	public static Stack<Object> pop(Stack<Object> mystack){
		if (!mystack.isEmpty()){
			mystack.pop();
		}
		else {
			mystack.push(":error:");
		}
		return mystack;
		//this function is complete. 
	}
	public static Stack<Object> Boolean (String input,Stack<Object> mystack){
		if (input.equals(":true:")){
			mystack.push(":true:");
		}
		else if (input.equals(":false:")){
			mystack.push(":false:");
		}
		else {
			mystack.push(":error:");
		}
		return mystack;
		//Boolean is complete.
	}

	public static Stack<Object> error (String input,Stack<Object> mystack){
		mystack.push(":error:");
		return mystack;
		// Error is complete but check again 
	}

	public static Stack<Object> add (Stack<Object> mystack){
		if (mystack.isEmpty()){
			mystack.push(":error:");
			return mystack;
		}

		Object Obj1 = mystack.peek();
		if (Obj1.getClass() == java.lang.Integer.class ){
			String input1 = mystack.pop().toString();
			if (mystack.isEmpty()){
				mystack.push(":error:");
				mystack.push(Obj1);
				return mystack;
			}
			Obj1 = mystack.peek();
			if (Obj1.getClass() == java.lang.Integer.class && !mystack.isEmpty()){
				String input2 = mystack.pop().toString();
				int sum = Integer.parseInt(input1, 10) + Integer.parseInt(input2, 10);
				mystack.push(sum);
			}
			else {
				mystack.push(":error:");
				mystack.push(input1);
			}
		}
		else {
			mystack.push(":error:");
		}
		return mystack;
		//complete the add methods also add errors .
	}



	public static Stack<Object> sub (Stack<Object> mystack){
		if (mystack.isEmpty()){
			mystack.push(":error:");
			return mystack;
		}
		Object Obj1 = mystack.peek();
		if (Obj1.getClass() == java.lang.Integer.class){
			String input1 = mystack.pop().toString();
			if (mystack.isEmpty()){
				mystack.push(":error:");
				mystack.push(Obj1);
				return mystack;
			}
			Obj1 = mystack.peek();
			if (Obj1.getClass() == java.lang.Integer.class && !mystack.isEmpty()){
				String input2 = mystack.pop().toString();
				int sum = Integer.parseInt(input2, 10) - Integer.parseInt(input1, 10);
				mystack.push(sum);
			}
			else {
				mystack.push(":error:");
				mystack.push(input1);
			}
		}
		else {
			mystack.push(":error:");
		}
		return mystack;
		//complete the sub methods also add errors .
	}


	public static Stack<Object> mul (Stack<Object> mystack){
		if (mystack.isEmpty()){
			mystack.push(":error:");
			return mystack;
		}

		Object Obj1 = mystack.peek();
		if (Obj1.getClass() == java.lang.Integer.class ){
			String input1 = mystack.pop().toString();
			if (mystack.isEmpty()){
				mystack.push(":error:");
				mystack.push(Obj1);
				return mystack;
			}
			Obj1 = mystack.peek();
			if (Obj1.getClass() == java.lang.Integer.class && !mystack.isEmpty()){
				String input2 = mystack.pop().toString();
				int sum = Integer.parseInt(input2, 10) * Integer.parseInt(input1, 10);
				mystack.push(sum);
			}
			else {
				
				mystack.push(":error:");
				mystack.push(input1);
			}
		}
		else {
			mystack.push(":error:");
		}
		return mystack;
		//complete the mul methods also add errors .
	}

	public static Stack<Object> div (Stack<Object> mystack){
		Object Obj1 = mystack.peek();
		if (Obj1.getClass() == java.lang.Integer.class ){
			String input1 = mystack.pop().toString();
			if (mystack.isEmpty()){
				mystack.push(":error:");
				mystack.push(Obj1);
				return mystack;
			}
			Obj1 = mystack.peek();
			if (Obj1.getClass() == java.lang.Integer.class && !mystack.isEmpty()){
				String input2 = mystack.pop().toString();
				int sum = Integer.parseInt(input2, 10) / Integer.parseInt(input1, 10);
				mystack.push(sum);
			}
			else {
				
				mystack.push(":error:");
				mystack.push(input1);
			}
		}
		else {
			mystack.push(":error:");
		}
		return mystack;
		//complete the div methods also add errors .
	}



	public static Stack<Object> rem (Stack<Object> mystack){
		if (mystack.isEmpty()){
			mystack.push(":error:");
			return mystack;
		}

		Object Obj1 = mystack.peek();
		if (Obj1.getClass() == java.lang.Integer.class ){
			String input1 = mystack.pop().toString();
			Obj1 = mystack.peek();
			if (Obj1.getClass() == java.lang.Integer.class && !mystack.isEmpty()){
				String input2 = mystack.pop().toString();
				int sum = Integer.parseInt(input2, 10) % Integer.parseInt(input1, 10);
				mystack.push(sum);
			}
			else {
				mystack.push(input1);
				mystack.push(":error:");
			}
		}
		else {
			mystack.push(":error:");
		}
		return mystack;
		//complete the rem methods also add errors .
	}


	public static Stack<Object> neg (Stack<Object> mystack){
		if (mystack.isEmpty()){
			mystack.push(":error:");
			return mystack;
		}

		Object Obj1 = mystack.peek();
		if (Obj1.getClass() == java.lang.Integer.class && !mystack.isEmpty()){
			String input1 = mystack.pop().toString();
			int sum = Integer.parseInt(input1, 10);
			int neg = -sum;
			mystack.push(neg);
		}
		else {
			mystack.push(":error:");
		}
		return mystack;

	}

	public static Stack<Object> swap (Stack<Object> mystack){
		if (!mystack.isEmpty()){
			Object input1 = mystack.pop();
			if (!mystack.isEmpty()){
				Object input2 = mystack.pop();
				mystack.push(input1);
				mystack.push(input2);
			}
			else {
				mystack.push(input1);
				mystack.push(":error:");
			}
		}
		else {
			mystack.push(":error:");
		}
		return mystack;

	}

	public static void quit(Stack<Object> myStack,FileOutputStream out) throws IOException{
		if (myStack.isEmpty()){
			System.out.println("This Stack is empty");
		}
		else {
			OutputStreamWriter osw = new OutputStreamWriter(out);
			System.out.println("the name is " + out.getFD());
			while (!myStack.isEmpty()){
				osw.write(myStack.peek().toString());
				System.out.println("in quit" + myStack.peek());
				myStack.pop();
			}
			osw.close();
			//print every element in the stack
		}
	}

	/*
	 * And function with perform logical and of two boolean values. 
	 * make sure your top two values in the stack are boolean and the stack it not empty
	 */
	public static Stack<Object> and (Stack<Object> mystack){
		Object Obj1 = mystack.peek();
		Boolean BObj1 = null;
		if (!mystack.isEmpty()){
			if (Obj1 == ":true:"){
				BObj1 = true;
			}
			else if(Obj1 == ":false:"){
				BObj1 = false;
			}
			else {
				mystack.push(Obj1);
				mystack.push(":error:");
				return mystack;
			}
			mystack.pop();
			if (!mystack.isEmpty()){
				Object Obj2 = mystack.peek();
				Boolean BObj2 = null;
				if (Obj2 == ":true:"){
					BObj2 = true;
				}
				else if(Obj2 == ":false:"){
					BObj2 = false;
				}
				else {
					mystack.push(Obj1);
					mystack.push(":error:");
					return mystack;
				}
				
				if (BObj1 == null |BObj2 == null){
					mystack.push(":error:");
				}
				
				else if ((BObj1 == true | BObj1 == false)&&(BObj2 == true | BObj2 == false)){
					Boolean myValue = BObj1 & BObj2; //Boolean logical And
					mystack.push(":"+myValue.toString()+":");
				}
			}
		}
		else {
			mystack.push(":error:");
		}

		return mystack;
	}


	/*
	 * Or function with perform logical OR of two boolean values. 
	 * make sure your top two values in the stack are boolean and the stack it not empty
	 */ 
	public static Stack<Object> or (Stack<Object> mystack){
		Object Obj1 = mystack.peek();
		Boolean BObj1 = null;
		if (!mystack.isEmpty()){
			if (Obj1 == ":true:"){
				BObj1 = true;
			}
			else if(Obj1 == ":false:"){
				BObj1 = false;
			}
			else {
				mystack.push(Obj1);
				mystack.push(":error:");
				return mystack;
			}
			mystack.pop();
			if (!mystack.isEmpty()){
				Object Obj2 = mystack.pop();
				Boolean BObj2 = null;
				if (Obj2 == ":true:"){
					BObj2 = true;
				}
				else if(Obj2 == ":false:"){
					BObj2 = false;
				}
				else {
					mystack.push(Obj1);
					mystack.push(":error:");
					return mystack;
				}
				
				if (BObj1 == null |BObj2 == null){
					mystack.push(":error:");
				}
				
				else if ((BObj1 == true | BObj1 == false)&&(BObj2 == true | BObj2 == false)){
					Boolean myValue = BObj1 | BObj2; //Boolean logical And
					mystack.push(":"+myValue.toString()+":");
				}
			}
		}
		else {
			mystack.push(":error:");
		}

		return mystack;
	}
	/*
	 * NOT function with perform logical NOT of two boolean values. 
	 * make sure your top two values in the stack are boolean and the stack it not empty
	 */ 
	public static Stack<Object> not (Stack<Object> mystack){
		Object Obj1 = mystack.peek();
		Boolean BObj1 = null;
		if (!mystack.isEmpty()){
			if (Obj1.toString().equals(":true:")){
				BObj1 = true;
			}
			else if(Obj1.toString().equals(":false:")){
				BObj1 = false;
			}
			mystack.pop();
			if (BObj1 == null){
				mystack.push(":error:");
				mystack.push(Obj1);
			}
			else if ((BObj1 == true | BObj1 == false)){
				Boolean myValue = !BObj1; //Boolean logical NOT
				mystack.push(":"+myValue.toString()+":");
			}
		
		else {
			mystack.push(":error:");
		}
		}
		return mystack;
	}

	public static Stack<Object> equal (Stack<Object> mystack){
		Object Obj1 = mystack.peek();
		if (Obj1.getClass() == Integer.class && !mystack.isEmpty()){
			mystack.pop();
			Object Obj2 = mystack.peek();

			if (Obj1.getClass() == Integer.class && !mystack.isEmpty()){
				if((int)Obj1 == (int)Obj2){ 
					mystack.push(":true:");
				}
				else {
					mystack.push("false");
				}
			}
			else {
				mystack.push(":error:");
			}
		}
		else {
			mystack.push(":error:");
		}

		return mystack;
	}

	public static Stack<Object> lessThan (Stack<Object> mystack){
		if (mystack.isEmpty()){
			mystack.push(":error:");// checking for empty stack to avoid null point error 
		}
		Object Obj1 = mystack.peek();
		if (Obj1.getClass() == Integer.class && !mystack.isEmpty()){
			mystack.pop();
			if (mystack.isEmpty()){
				mystack.push(":error:"); // checking for empty stack to avoid null point error }
				Object Obj2 = mystack.peek();
				if (Obj2.getClass() == Integer.class && !mystack.isEmpty()){
					if((int)Obj1 < (int)Obj2){ 
						mystack.push(":true:");}
					if((int)Obj1 > (int)Obj2){ 
						mystack.push(":false:");}
				}
				else {
					mystack.push(":error:");}
			}
			else {
				mystack.push(":error:");}
		}
		return mystack;
	}

	public static Stack<Object> bind (Stack<Object> mystack,Map<Object, pushType> myMap,Map<Object, Object> myMapBind){
		if (mystack.isEmpty()){
			mystack.push(":error:");
			return mystack;
		}
		Object value = mystack.pop();
		if (mystack.isEmpty()){
			mystack.push(value);
			mystack.push(":error:");
			return mystack;
		}
		String name = mystack.pop().toString();
		//creating 2 envs maps and puting the values in it with the binding. 
		if(myMap.get(name) == pushType.NAME){
			if(myMapBind.containsKey(name)){
				myMapBind.replace(name, value);
			}
			else {
				myMapBind.put(name,value);
			}
			mystack.push(":unit:");//The result of a bind operation is :unit: which is pushed onto the stack.
		}
		else {
			mystack.push(":error:");
		}
		
		return mystack;
	}

	public static Stack<Object> _if_ (Stack<Object> mystack){
		if(mystack.isEmpty()){
			mystack.push("error:");
		}
		//poping first element 
		Object x = mystack.pop();
		if(mystack.isEmpty()){
			mystack.push(x);
			mystack.push("error:");
		}
		//poping second element
		Object y = mystack.pop();
		if(mystack.isEmpty()){
			mystack.push(y);
			mystack.push(x);
			mystack.push("error:");
		}
		//poping third element
		Object z = mystack.pop();
		if (z == ":true:"){
			mystack.push(x); //If z is :true:, if has the value of x
		}
		else if (z == ":false:"){
			mystack.push(y); //if z is :false:, if has the value y
		}
		else {
			mystack.push("error:");
		}
		return mystack;
	}

	
	public static Stack<Object> Let (Stack<Object> mystackLet,Stack<Object> myStackletEnd,Map<Object, pushType> myMap,Map<Object, Object> myMapBind){
		return mystackLet; //TODO
		
	}
	public static Object End (Stack<Object> myStackletEnd,Map<Object, pushType> myMap,Map<Object, Object> myMapBind){
	if (myStackletEnd.peek().toString().equals("and")){
		and(myStackletEnd);
	}
	if (myStackletEnd.peek().toString().equals("or")){
		or(myStackletEnd);
	}
	if (myStackletEnd.peek().toString().equals("not")){
		not(myStackletEnd);
	}
	if (myStackletEnd.peek().toString().equals("equal")){
		equal(myStackletEnd);
	}
	if (myStackletEnd.peek().toString().equals("lessThan")){
		lessThan(myStackletEnd);
	}
	if (myStackletEnd.peek().toString().length() > 3 && myStackletEnd.peek().toString().substring(0,4).equals("push")){
		push_num_(myStackletEnd.peek().toString(), myStackletEnd, myMap);
		System.out.println("In push "+ myStackletEnd.peek());

	}
	if (myStackletEnd.peek().toString().equals("pop")){
		pop(myStackletEnd);
	}
	if (myStackletEnd.peek().toString().equals(":true:") | myStackletEnd.peek().toString().equals(":false:")){
		Boolean(myStackletEnd.peek().toString(), myStackletEnd);
		System.out.println("in boolean"+myStackletEnd.peek());
	}
	if (myStackletEnd.peek().toString().equals(":error:")){
	error(myStackletEnd.peek().toString(), myStackletEnd);
	}
	if (myStackletEnd.peek().toString().equals("add")){
		add(myStackletEnd);
	}
	if (myStackletEnd.peek().toString().equals("sub")){
		sub(myStackletEnd);
	}
	if (myStackletEnd.peek().toString().equals("mul")){
		mul(myStackletEnd);
	}
	if (myStackletEnd.peek().toString().equals("div")){
		div(myStackletEnd);
	}
	if (myStackletEnd.peek().equals("rem")){
		rem(myStackletEnd);
	}
	if (myStackletEnd.peek().toString().equals("neg")){
		neg(myStackletEnd);
	}
	if (myStackletEnd.peek().toString().equals("swap")){
		swap(myStackletEnd);
	}
	else {
		bind(myStackletEnd, myMap, myMapBind);
		swap(myStackletEnd);
		myStackletEnd.pop();
	}
	bind(myStackletEnd, myMap, myMapBind);
	return myStackletEnd.peek();
}
	
	public static void hw3(FileReader input, FileOutputStream output) throws IOException{
		BufferedReader in = new BufferedReader(input);
		FileOutputStream out = output;
		String line;
		Stack<Object> myStack = new Stack<>();
		Stack<Object> myStackLet = new Stack<>();
		Map<Object,pushType> myMap = new HashMap<>();
		Map<Object,Object> myMapBind = new HashMap<>();

		while ((line = in.readLine())!= null){
			if (line.toString().equals("and")){
				and(myStack);
			}
			if (line.toString().equals("or")){
				or(myStack);
			}
			if (line.toString().equals("not")){
				not(myStack);
			}
			if (line.toString().equals("equal")){
				equal(myStack);
			}
			if (line.toString().equals("lessThan")){
				lessThan(myStack);
			}
			if (line.length() > 3 && line.substring(0,4).equals("push")){
				if(myStackLet.contains("let")){
					push_num_(line, myStackLet, myMap);
				}
				else {
					push_num_(line, myStack, myMap);
				}
			}
			if (line.toString().equals("pop")){
				pop(myStack);
			}
			if (line.toString().equals(":true:") | line.toString().equals(":false:")){
				Boolean(line, myStack);
				System.out.println("in boolean"+myStack.peek());
			}
			if (line.toString().equals(":error:")){
			error(line, myStack);
			}
			if (line.toString().equals("add")){
				add(myStack);
			}
			if (line.toString().equals("sub")){
				sub(myStack);
			}
			if (line.toString().equals("mul")){
				mul(myStack);
			}
			if (line.toString().equals("div")){
				div(myStack);
			}
			if (line.equals("rem")){
				rem(myStack);
			}
			if (line.toString().equals("neg")){
				neg(myStack);
			}
			if (line.toString().equals("swap")){
				swap(myStack);
			}
			if (line.toString().equals("quit")){
				quit(myStack,out);
			}
			if (line.toString().equals("bind")){
				bind(myStack, myMap, myMapBind);
			}
			if (line.toString().equals("if")){
				_if_(myStack);
			}
			if (line.toString().equals("let")){
				myStackLet.push("let");
				Let(myStack, myStackLet, myMap, myMapBind);
			}
			if (line.equals("end")){
				End(myStackLet, myMap, myMapBind);
				while(!myStackLet.peek().toString().equals("let")){
					myStackLet.pop();
				}
				if (myStackLet.peek().toString().equals("let")){
					myStackLet.pop();
					}
				}
			}
		}

public static void main(String [ ] args) throws IOException{
 System.out.println("i am in main");

		FileReader input7 = new FileReader("input_1.txt");//correct
		FileOutputStream output7 = new FileOutputStream("output1.txt");
		hw_3.hw3(input7, output7);
		FileReader input9 = new FileReader("input_2.txt");//correct
		FileOutputStream output9 = new FileOutputStream("output3.txt");
		hw_3.hw3(input9, output9);
		FileReader input10 = new FileReader("input_3.txt");//correct
		FileOutputStream output10 = new FileOutputStream("output4.txt");
		hw_3.hw3(input10, output10);
		
		
		
		
		FileReader input11 = new FileReader("input_4.txt");//Should be true 
		FileOutputStream output11 = new FileOutputStream("output5.txt");
		hw_3.hw3(input11, output11);
		FileReader input12 = new FileReader("input_5");//mine have an extra 3 giving 5 :error: 3 3 :true:
		FileOutputStream output12 = new FileOutputStream("output6.txt");
		hw_3.hw3(input12, output12);
		FileReader input13 = new FileReader("input_6");//should be true false a 
		FileOutputStream output13 = new FileOutputStream("_output6.txt");
		hw_3.hw3(input13, output13);
		FileReader input14 = new FileReader("input_7"); //CORRECT
		FileOutputStream output14 = new FileOutputStream("_output6.txt");
		hw_3.hw3(input14, output14);
		FileReader input15 = new FileReader("input_8"); //should be 5 unit unit
		FileOutputStream output15 = new FileOutputStream("sample_output6.txt");
		hw_3.hw3(input15, output15);
		
		
		FileReader input16 = new FileReader("input_9");//correct
		FileOutputStream output16 = new FileOutputStream("e_output6.txt");
		hw_3.hw3(input16, output16);

     System.out.println("i am done with main");

    }
}

