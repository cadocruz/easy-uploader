-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE videos_video_media (
                                    id VARCHAR(16) NOT NULL PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL,
                                    checksum VARCHAR(255) NOT NULL,
                                    file_path VARCHAR(500) NOT NULL,
                                    encoded_path VARCHAR(500) NOT NULL,
                                    media_status VARCHAR(50) NOT NULL
);

CREATE TABLE videos (
                        id VARCHAR(16) NOT NULL PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        description VARCHAR(1000) NOT NULL,
                        year_launched INT NOT NULL,
                        opened BOOLEAN NOT NULL DEFAULT FALSE,
                        published BOOLEAN NOT NULL DEFAULT FALSE,
                        rating VARCHAR(5),
                        duration NUMERIC(5, 2) NOT NULL,
                        created_at TIMESTAMP NOT NULL,
                        updated_at TIMESTAMP,
                        video_id VARCHAR(16) NULL,
                        CONSTRAINT fk_v_video_id FOREIGN KEY (video_id) REFERENCES videos_video_media (id) ON DELETE CASCADE
);