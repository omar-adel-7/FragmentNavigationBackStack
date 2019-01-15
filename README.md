Android project that uses FragmentHelper class for managing stack of fragments

I use add fragment not replace and show or hide fragment depending on usage case :

Features :

1 - if you want to open fragment and if it is already loaded before , you want to resume it from stack

then use :

 fragmentHelper.showFragment(fragment,false);

 2 -  if you want to open fragment and if it is already loaded before , you want to remove the previous one from stack and create new one

then use :

 fragmentHelper.showFragment(fragment,true);


3 -  if you want to open fragment class multiple times for example TestFragment.class i.e : create new one for each fragment without removing the previous ones

then use :

fragmentHelper.showFragmentWithRepeat(fragment);

 
4 - you can remove any instance of certain fragment by its class name

  fragmentHelper.removeFragment(TestFragment.class.getName());
 
5 - you can remove all fragments except any instance of fragment with certain class name

 fragmentHelper.removeAllFragmentsExceptOne(TestFragment.class.getName());


as you see , you have this project to show usage of this class
