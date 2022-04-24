txt = Hello world
source.txt.template:
	@# will generate source.txt.template
	echo  $(txt) > source.txt.template

source.txt:
	# will generate source.txt
	cp source.txt.template source.txt

# You can cleanup last build files
clean:
	@# will clean
	@rm -f source.txt source.txt.template