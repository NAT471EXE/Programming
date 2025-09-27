// Recursively lists txt files in a directory
#include <stdio.h>
#include <stdlib.h>
#include <dirent.h>
#include <errno.h>
#include <string.h>
#include <sys/stat.h>

void list_txt_files(const char *path)
{

    DIR *dir = opendir(path);
    if (!dir)
    {
        perror("opendir");
        return;
    }

    struct dirent *entry;
    char fullpath[4096];

    while ((entry = readdir(dir)) != NULL)
    {

        if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0)
            continue;

        snprintf(fullpath, sizeof(fullpath), "%s/%s", path, entry->d_name);

        struct stat st;
        if (stat(fullpath, &st) == -1)
        {
            perror("stat");
            continue;
        }

        if (S_ISDIR(st.st_mode))
        {
            list_txt_files(fullpath);
        }
        else
        {
            const char *ext = strrchr(entry->d_name, '.');
            if (ext && strcmp(ext, ".txt") == 0)
            {
                printf("%s\n", fullpath);
            }
        }
    }
    closedir(dir);
}

int main(void)
{
    const char *path = "/home/user/Documents";
    list_txt_files(path);
    return EXIT_SUCCESS;
}
