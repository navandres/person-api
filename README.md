To deploy the application open a terminal window and follow the guide

Make sure you have minikube installed and running.

Then you need to make sure the terminal is appropriatly pointing to
the minikube's docker repository by running:

	>$ eval $(minikube docker-env)
	

then will make sure we are at the root folder of the project,
just where the pom.xml file is.

Then will make docker build our application and deploy the image
in the minikube's docker image repository.

	>$ docker build -t springio/person-api .
	

the -t flag denotes the tag name the image will get at the repository.
Ones the build process is doen you should see the image now inside the
repository by:

	>$ docker image ls
	
    REPOSITORY                    TAG                 IMAGE ID            CREATED             SIZE
    springio/person-api           latest              4749acbab22d        34 seconds ago      124MB


This means that now minikube should be able to pull the image from the repo when
we publish the application into the minikube cluster.

Lets publish the application now:
	
	>$ kubectl create -f deployments/local/
	

This command tells minikube to deploy the image, so once it's done you should
see the pods running into the minikube cluser:

	>$ kubectl get pods
	NAME                          READY   STATUS    RESTARTS   AGE
	person-api-77c56dff9f-j68rh   1/1     Running   0           4m

This is a test.