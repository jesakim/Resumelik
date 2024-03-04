#!/bin/bash

# Change directory to the target directory
cd ../model

repository_path=../repository
controller_path=../controller
service_path=../service
request_dto_path=../dto/request
response_dto_path=../dto/response



# Loop through each file in the directory
for file in *; do
    # Check if the current item is a file
    if [ -f "$file" ]; then
        # Print the file name
        new_name="${file%.java}"

        echo "$new_name"
        if [ ! -f "$repository_path/${new_name}Repository.java" ]; then
                # If the file doesn't exist, create it
                touch "$repository_path/${new_name}Repository.java"
                echo "package com.example.resumlik.repository;" >> "$repository_path/${new_name}Repository.java"
                echo "" >> "$repository_path/${new_name}Repository.java"
                echo "import com.example.resumlik.model.${new_name};" >> "$repository_path/${new_name}Repository.java"
                echo "import org.springframework.data.jpa.repository.JpaRepository;" >> "$repository_path/${new_name}Repository.java"
                echo "import org.springframework.stereotype.Repository;" >> "$repository_path/${new_name}Repository.java"
                echo "" >> "$repository_path/${new_name}Repository.java"
                echo "@Repository" >> "$repository_path/${new_name}Repository.java"
                echo "public interface ${new_name}Repository extends JpaRepository<${new_name}, Long> {" >> "$repository_path/${new_name}Repository.java"
                echo "}" >> "$repository_path/${new_name}Repository.java"


                echo "Created ${new_name}Repository.java"
        else
            echo "$repository_path/${new_name}Repository.java already exists"
        fi

        if [ ! -f "$controller_path/${new_name}Controller.java" ]; then
                # If the file doesn't exist, create it
                touch "$controller_path/${new_name}Controller.java"
                echo "package com.example.resumlik.controller;" >> "$controller_path/${new_name}Controller.java"
                echo "" >> "$controller_path/${new_name}Controller.java"
                echo "import com.example.resumlik.model.${new_name};" >> "$controller_path/${new_name}Controller.java"
                echo "import com.example.resumlik.repository.${new_name}Repository;" >> "$controller_path/${new_name}Controller.java"
                echo "import lombok.RequiredArgsConstructor;" >> "$controller_path/${new_name}Controller.java"
                echo "import org.springframework.http.ResponseEntity;" >> "$controller_path/${new_name}Controller.java"
                echo "import jakarta.validation.Valid;" >> "$controller_path/${new_name}Controller.java"
                echo "import org.springframework.web.bind.annotation.*;" >> "$controller_path/${new_name}Controller.java"
                echo "" >> "$controller_path/${new_name}Controller.java"
                echo "@RestController" >> "$controller_path/${new_name}Controller.java"
                echo "@RequestMapping(\"/api/${new_name}\")" >> "$controller_path/${new_name}Controller.java"
                echo "@RequiredArgsConstructor" >> "$controller_path/${new_name}Controller.java"
                echo "public class ${new_name}Controller {" >> "$controller_path/${new_name}Controller.java"
                echo "}" >> "$controller_path/${new_name}Controller.java"

                echo "Created ${new_name}Controller.java"
        else
            echo "$controller_path/${new_name}Controller.java already exists"
        fi

        if [ ! -f "$service_path/${new_name}Service.java" ]; then
                # If the file doesn't exist, create it
                touch "$service_path/${new_name}Service.java"
                echo "package com.example.resumlik.service;" >> "$service_path/${new_name}Service.java"
                echo "" >> "$service_path/${new_name}Service.java"
                echo "import com.example.resumlik.model.${new_name};" >> "$service_path/${new_name}Service.java"
                echo "import com.example.resumlik.repository.${new_name}Repository;" >> "$service_path/${new_name}Service.java"
                echo "import lombok.RequiredArgsConstructor;" >> "$service_path/${new_name}Service.java"
                echo "import org.springframework.stereotype.Service;" >> "$service_path/${new_name}Service.java"
                echo "" >> "$service_path/${new_name}Service.java"
                echo "@Service" >> "$service_path/${new_name}Service.java"
                echo "@RequiredArgsConstructor" >> "$service_path/${new_name}Service.java"
                echo "public class ${new_name}Service {" >> "$service_path/${new_name}Service.java"
                echo "}" >> "$service_path/${new_name}Service.java"

                echo "Created ${new_name}Service.java"
        else
            echo "$service_path/${new_name}Service.java already exists"
        fi


        #package com.example.resumlik.dto.reponse;
                 #
                 #
                 #import com.example.resumlik.model.Resume;
                 #import lombok.AllArgsConstructor;
                 #import lombok.Getter;
                 #import lombok.NoArgsConstructor;
                 #import lombok.Setter;
                 #
                 #@AllArgsConstructor
                 #@Setter
                 #@Getter
                 #@NoArgsConstructor
                 #public class ResumeRequestDto { 'follow the same pattern for the rest of the files'#

        if [ ! -f "$request_dto_path/${new_name}RequestDto.java" ]; then
                # If the file doesn't exist, create it
                touch "$request_dto_path/${new_name}RequestDto.java"
                echo "package com.example.resumlik.dto.request;" >> "$request_dto_path/${new_name}RequestDto.java"
                echo "" >> "$request_dto_path/${new_name}RequestDto.java"
                echo "import com.example.resumlik.model.${new_name};" >> "$request_dto_path/${new_name}RequestDto.java"
#                echo "import lombok.AllArgsConstructor;" >> "$request_dto_path/${new_name}RequestDto.java"
                echo "import lombok.Getter;" >> "$request_dto_path/${new_name}RequestDto.java"
#                echo "import lombok.NoArgsConstructor;" >> "$request_dto_path/${new_name}RequestDto.java"
                echo "import lombok.Setter;" >> "$request_dto_path/${new_name}RequestDto.java"
                echo "" >> "$request_dto_path/${new_name}RequestDto.java"
#                echo "@AllArgsConstructor" >> "$request_dto_path/${new_name}RequestDto.java"
                echo "@Setter" >> "$request_dto_path/${new_name}RequestDto.java"
                echo "@Getter" >> "$request_dto_path/${new_name}RequestDto.java"
#                echo "@NoArgsConstructor" >> "$request_dto_path/${new_name}RequestDto.java"
                echo "public class ${new_name}RequestDto {" >> "$request_dto_path/${new_name}RequestDto.java"
                echo "}" >> "$request_dto_path/${new_name}RequestDto.java"

                echo "Created ${new_name}RequestDto.java"
        else
            echo "$request_dto_path/${new_name}RequestDto.java already exists"
        fi


        # do the same for response dto

        if [ ! -f "$response_dto_path/${new_name}ResponseDto.java" ]; then
                # If the file doesn't exist, create it
                touch "$response_dto_path/${new_name}ResponseDto.java"
                echo "package com.example.resumlik.dto.response;" >> "$response_dto_path/${new_name}ResponseDto.java"
                echo "" >> "$response_dto_path/${new_name}ResponseDto.java"
                echo "import com.example.resumlik.model.${new_name};" >> "$response_dto_path/${new_name}ResponseDto.java"
#                echo "import lombok.AllArgsConstructor;" >> "$response_dto_path/${new_name}ResponseDto.java"
                echo "import lombok.Getter;" >> "$response_dto_path/${new_name}ResponseDto.java"
#                echo "import lombok.NoArgsConstructor;" >> "$response_dto_path/${new_name}ResponseDto.java"
                echo "import lombok.Setter;" >> "$response_dto_path/${new_name}ResponseDto.java"
                echo "" >> "$response_dto_path/${new_name}ResponseDto.java"
#                echo "@AllArgsConstructor" >> "$response_dto_path/${new_name}ResponseDto.java"
                echo "@Setter" >> "$response_dto_path/${new_name}ResponseDto.java"
                echo "@Getter" >> "$response_dto_path/${new_name}ResponseDto.java"
#                echo "@NoArgsConstructor" >> "$response_dto_path/${new_name}ResponseDto.java"
                echo "public class ${new_name}ResponseDto {" >> "$response_dto_path/${new_name}ResponseDto.java"
                echo "}" >> "$response_dto_path/${new_name}ResponseDto.java"

                echo "Created ${new_name}ResponseDto.java"
        else
            echo "$response_dto_path/${new_name}ResponseDto.java already exists"
        fi

    fi
done
